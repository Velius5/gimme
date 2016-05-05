/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import velius.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findById(long id);

    User findByEmailAndPassword(String email, String password);
    
    User findByEmail(String email); 
    
    List<User> findTop30BySurnameContainingAndNameContainingOrderByName(String surname, String name);
   
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'true' ELSE 'false' END FROM User u WHERE u.id = ?1")
    Boolean exists(Long id);
    

    

}
