
package velius.controller.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.jsoup.nodes.DataNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import velius.model.ModelProductHistory;
import velius.model.Product;
import velius.model.User;
import velius.service.ProductService;
import velius.service.UserService;

/**
 *
 * @author Piotr Czarny
 */

@RestController
@RequestMapping("/api/history")
public class HistoryApiController {

    @Autowired
    UserService userService;
    
    @Autowired
    ProductService productService;
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public List<ModelProductHistory> getProductsHistory(@PathVariable("id") Long id){
        User user = userService.getUser(id);
        List<ModelProductHistory> responseList = null;
        
        List<Product> myDebts = productService.getMyDebtsHistory(user);
        myDebts.addAll(productService.getUserDebitorsHistory(user));
      
        
        Collections.sort(myDebts, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o1.getReceipt().getDate().equals(o2.getReceipt().getDate())) {
                    return 0;
                }
                return o1.getReceipt().getDate().before(o2.getReceipt().getDate())  ? -1 : 1;
            }

            
        });
        
        List<ModelProductHistory> productHistoryList = new ArrayList<ModelProductHistory>();
        for(Product prod : myDebts){
            ModelProductHistory temp = new ModelProductHistory();
            temp.setDate(prod.getReceipt().getDate());
            temp.setProduct(prod);
            temp.setOwner(prod.getOwner());
            
            temp.setUsers(prod.getUsers());
            temp.setPaidUsers(prod.getUsersHistory());
            
            productHistoryList.add(temp);
        }
        return productHistoryList;
    }
}
