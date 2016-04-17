/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import velius.model.Product;
import velius.model.User;

/**
 *
 * @author Patryk
 */
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findIdDistinctByOwnerAndUsersNotNull(User user);
    List<Product> findAllByUsers(User user);
}
