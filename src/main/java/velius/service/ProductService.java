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
    
    List<Product>  getUserDebitors(User user);

    public List<Product> getMyDebts(User user);
}
