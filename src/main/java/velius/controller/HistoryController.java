
package velius.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String historyPage(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userService.getUserByEmail(email);
        
        List<Product> productList = user.getProductsHistory();
        
        return "panel_history";
    }
}
