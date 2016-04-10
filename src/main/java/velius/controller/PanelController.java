/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.controller;

import java.util.List;
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


@Controller
public class PanelController {
    
    @Autowired
    UserService userService;
    
    @Autowired
    ReceiptService receiptService;
    
    @Autowired
    ProductService productService;
    
    @RequestMapping("/panel")
    public String panelPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        
        User user = userService.getUserByEmail(email);
        List<Receipt> receiptList = receiptService.findAllByOwner(user);
        model.addAttribute("paragony", receiptList);
        
        List<Product> debtorList = productService.getUserDebitors(user);
        model.addAttribute("produktyDluznikow", debtorList);
        
        List<Product> myDebts = productService.getMyDebts(user);
        model.addAttribute("mojeDlugi", myDebts);
        
        
        
        
        return "panel";
    }
}
