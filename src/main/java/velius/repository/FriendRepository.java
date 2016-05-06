
package velius.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import velius.model.Friend;
import velius.model.User;

/**
 *
 * @author Piotr Czarny
 */
public interface FriendRepository extends JpaRepository<Friend, String>{
    //Friend find(Long id);
    
    List<Friend> findAllByUserId(User user);
    List<Friend> findAllByFriendId(User user);
    Friend findByUserIdAndFriendId(User user,User friend);
    
}
