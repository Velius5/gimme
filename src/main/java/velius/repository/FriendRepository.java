
package velius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import velius.model.Friend;

/**
 *
 * @author Piotr Czarny
 */
public interface FriendRepository extends JpaRepository<Friend, String>{
    //Friend find(Long id);
}
