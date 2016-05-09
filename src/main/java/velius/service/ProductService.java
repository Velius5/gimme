/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.service;

import java.util.List;
import velius.model.Product;
import velius.model.User;

/**
 *
 * @author Patryk
 */
public interface ProductService {
    Product save(Product Product);
    
    Product getProduct(Long id);
    
    List<Product>  getUserDebitors(User user);
    
    List<Product>  getUserDebitorsHistory(User user);

    public List<Product> getMyDebts(User user);
    
    public List<Product> getMyDebtsHistory(User user);
    
    public List<Product> getMyDebtsToFriend(User user, User friend);
    
    public List<Product> getFriendDebtsToMe(User user, User friend);
    
    public List<Product> getAll();
    
    
}
