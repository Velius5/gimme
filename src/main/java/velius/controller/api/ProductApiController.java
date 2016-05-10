
package velius.controller.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import velius.model.Product;
import velius.model.Response;
import velius.model.User;
import velius.service.ProductService;
import velius.service.UserService;

/**
 *
 * @author Piotr Czarny
 */

@RestController
@RequestMapping(value = "/api/product")
public class ProductApiController {
    @Autowired
    UserService userService;
    
    @Autowired
    ProductService productService;
    
    @RequestMapping(value = "/debts/{id}", method = RequestMethod.GET)
    List<Product> getMyDebts(@PathVariable("id") Long id){
        User user = userService.getUser(id);
        return productService.getMyDebts(user);
    }
    
    @RequestMapping(value = "/debtors/{id}", method = RequestMethod.GET)
    List<Product> getMyDebtors(@PathVariable("id") Long id){
        User user = userService.getUser(id);
        return productService.getUserDebitors(user);
    }
    
    @RequestMapping(value = "/mydebts/{id}/{friendid}", method = RequestMethod.GET)
    List<Product> getMyDebtsToFriend(@PathVariable("id") Long id,@PathVariable("friendid") Long friendid){
        User user = userService.getUser(id);
        User friend = userService.getUser(friendid);
        
        List<Product> prods = productService.getMyDebtsToFriend(user, friend);
        return prods;
    }
    
    @RequestMapping(value = "/payoffalldebts/{id}/{friendid}", method = RequestMethod.GET)
    Response payoffAllDebts(@PathVariable("id") Long id,@PathVariable("friendid") Long friendid){
        User user = userService.getUser(id);
        User friend = userService.getUser(friendid);
        
        List<Product> prods = productService.getFriendDebtsToMe(user, friend);
        if(prods != null) {
            List<Product> friendProducts = friend.getProducts();
            List<Product> friendHistory = friend.getProductsHistory();
            
            for(Product prod : prods){
                prod.getUsers().remove(friend);
                productService.save(prod);
            }     
            
            friendHistory.addAll(prods);
            friend.setProductsHistory(friendHistory);
            
            userService.save(friend);
            
            return new Response(true);
        } else {
            return new Response(false);
        }
    }
    
    @RequestMapping(value = "/friendsdebts/{id}/{friendid}", method = RequestMethod.GET)
    List<Product> getFriendDebtsToMe(@PathVariable("id") Long id,@PathVariable("friendid") Long friendid){
        User user = userService.getUser(id);
        User friend = userService.getUser(friendid);
        
        
        
        List<Product> prods = productService.getFriendDebtsToMe(user, friend);
        for(Product prod : prods)
            prod.setPrice(prod.getPricePerPerson());
        return prods;
    }
}
