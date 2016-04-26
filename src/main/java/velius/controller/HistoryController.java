
package velius.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import velius.model.ModelProductHistory;
import velius.model.Product;
import velius.model.User;
import velius.service.ProductService;
import velius.service.UserService;

/**
 *
 * @author Piotr Czarny
 */

@Controller
@RequestMapping("/panel/history")
public class HistoryController {
    @Autowired
    UserService userService;
    
    @Autowired
    ProductService productService;
   
    
    @RequestMapping("/")
    public String historyPage(Model model,Principal principal){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userService.getUserByEmail(email);
        
        List<Product> myDebtsHistoryList = productService.getMyDebtsHistory(user);
        List<Product> myProductsHistoryList = productService.getUserDebitorsHistory(user);
        
        List<ModelProductHistory> modelMyDebstList = new ArrayList();
        for(Product prod : myDebtsHistoryList){
            ModelProductHistory temp = new ModelProductHistory();
            temp.setProduct(prod);
            temp.setDate(prod.getReceipt().getDate());
            modelMyDebstList.add(temp);
        }
        model.addAttribute("historiaMoje", modelMyDebstList);
        
        List<ModelProductHistory> modelMyProductList = new ArrayList();
        for(Product prod : myProductsHistoryList){
            ModelProductHistory temp = new ModelProductHistory();
            temp.setProduct(prod);
            temp.setDate(prod.getReceipt().getDate());
            modelMyDebstList.add(temp);
        }
        model.addAttribute("historiaInni", modelMyProductList);
        
        return "panel_history";
    }
}
