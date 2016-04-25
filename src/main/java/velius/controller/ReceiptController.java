
package velius.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.piotr.TessOCR;
import velius.model.Receipt;
import velius.model.User;
import velius.service.ProductService;
import velius.service.ReceiptService;
import velius.service.UserService;

/**
 *
 * @author Piotr Czarny
 */

@Controller
@RequestMapping("/panel/receipts")
public class ReceiptController {
    @Autowired
    ReceiptService receiptService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    ProductService productService;
    
    @RequestMapping("/")
    public String receiptPage(Model model, Principal principal){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userService.getUserByEmail(email);
        
        List<Receipt> receiptsList = receiptService.findAllByOwner(user);
        
        model.addAttribute("paragony", receiptsList);
        
        return "panel_receipts";
    }
    
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String showReceiptPage(Model model, Principal principal,@PathVariable("id") Long id){
        Receipt receipt = receiptService.findById(id);
        
        model.addAttribute("paragon",receipt);
        
        return "panel_receipt_show_";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteReceipt(Model model, Principal principal,@PathVariable Long id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        
        User owner = userService.getUserByEmail(email);
        
        Receipt receipt = receiptService.findById(id);
        if(owner.equals(receipt.getOwner())){
            receiptService.deleteReceipt(receipt);
        }
        
        return "panel_receipts";
    }
    
    @RequestMapping("/add")
    public String addReceiptPage(){
        return "receipt_add";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addReceiptPhoto(Model model, Principal principal, @RequestParam(value = "photo", required = false) MultipartFile photo) throws IOException{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        
        User owner = userService.getUserByEmail(email);
        
        byte[] imgBytes = photo.getBytes(); 
        pl.piotr.ReceiptsTemplates.Receipt temp = TessOCR.recognizeReceipt(imgBytes);
        Receipt receipt = new Receipt(temp,imgBytes,owner);
        receiptService.save(receipt);
        
        model.addAttribute(receipt);
        return "receipt_add_step1";
    }
    @RequestMapping(value = "/add/step1", method = RequestMethod.POST)
    public String editProductsPage(Model model, Principal principal){
        
        return "receipt_add_step2";
    }
}