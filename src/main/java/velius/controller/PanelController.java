/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import velius.model.Product;
import velius.model.Receipt;
import velius.model.User;
import velius.service.ProductService;
import velius.service.ReceiptService;
import velius.service.UserService;

/**
 * Kontroler obsługujący zapytania wysyłane przez przeglądarkę po zalogowaniu 
 * się przez użytkownika. Dostarcza dane do widoku strony głównej dla 
 * zalogowanych użytkowników
 */
@Controller
public class PanelController {
    
    @Autowired
    UserService userService;
    
    @Autowired
    ReceiptService receiptService;
    
    @Autowired
    ProductService productService;
    
    @RequestMapping("/panel")
    public String panelPage(Model model, Principal principal) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userService.getUserByEmail(email);
        model.addAttribute("userName", user.getName());
        
        List<Receipt> receiptList = receiptService.findLast6ByOwner(user);
        List<Receipt> receiptListWithBase64EncodedImages = new ArrayList<>();
        for (int i = 0; i < receiptList.size(); i++) {
            Receipt rec = receiptList.get(i);
            String base64Image = Base64.encodeBase64String(rec.getImage());
            rec.setDescription(base64Image);
            receiptListWithBase64EncodedImages.add(rec);
        }
        model.addAttribute("paragony", receiptListWithBase64EncodedImages);
        
        List<Product> debtorList = productService.getUserDebitors(user);
        model.addAttribute("produktyDluznikow", debtorList);
        
        List<Product> myDebts = productService.getMyDebts(user);
        model.addAttribute("mojeDlugi", myDebts);
        
        BigDecimal saldo = BigDecimal.ZERO;
        
        for (Product prod : debtorList) {
            saldo = saldo.add(prod.getPricePerPerson());
        }
        int incoming = saldo.intValue();
        for (Product prod : myDebts) {
            saldo = saldo.subtract(prod.getPricePerPerson());
        }
        int outcoming = incoming - saldo.intValue();
        model.addAttribute("bilans", saldo+" zł");
        model.addAttribute("przychody",incoming);
        model.addAttribute("wydatki", outcoming);
        return "panel";
    }
}
