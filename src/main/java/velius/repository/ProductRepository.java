/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import velius.model.Product;

/**
 *
 * @author Patryk
 */
public interface ProductRepository extends JpaRepository<Product, String> {
    
}
