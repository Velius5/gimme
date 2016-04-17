<<<<<<< HEAD
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
    List<Product> findAllByOwner(User user);
    List<Product> findAllByUsers(User user);
}
=======
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
>>>>>>> cbf69cf021552409f2e3d375a47d9da42d236b43
