/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import velius.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findById(long id);

    User findByEmailAndPassword(String email, String password);
    
    User findByEmail(String email); 
    

}
