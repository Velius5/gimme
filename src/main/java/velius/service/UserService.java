/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.service;

import java.util.List;
import velius.model.User;

public interface UserService {
    
    User save(User user);

    List<User> getList();
    
    User getUser(long id);

    public User getUserByEmailAndPassword(String email, String password);
    
}
