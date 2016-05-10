
package velius.controller.api;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import velius.model.Notification;
import velius.model.Product;
import velius.model.Response;
import velius.model.User;
import velius.service.NotificationService;
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
    
    @Autowired
    NotificationService notificationService;
    
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
    
    @RequestMapping(value = "/payoffallfrienddebts/{id}/{friendid}", method = RequestMethod.GET)
    Response payoffAllDebts(@PathVariable("id") Long id,@PathVariable("friendid") Long friendid){
        User user = userService.getUser(id);
        User friend = userService.getUser(friendid);
        for(Product p : friend.getProducts())
            System.out.println(p.toString());
        
        List<Product> prods = productService.getFriendDebtsToMe(user, friend);
        if(prods != null & prods.size() > 0) {
            List<Product> friendProducts = friend.getProducts();
            for(Product prod : prods) {
                friend.getProductsHistory().add(prod);
                friendProducts.remove(prod);
                System.out.println(prod.toString());
            }
            friend.setProducts(friendProducts);
            userService.save(friend);
            notificationService.save(new Notification("receipt", new Date(), "Użytkownik " + friend.getName() + " " + friend.getSurname() + " uregulował długi wobec Ciebie.", true, user.getId()));
            notificationService.save(new Notification("receipt", new Date(), "Uregulowałeś długi wobec użytkownika " + user.getName() + " " + user.getSurname(), false, friend.getId()));
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
