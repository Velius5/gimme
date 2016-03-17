
package velius.controller.api;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import velius.model.Friend;
import velius.model.User;
import velius.service.UserService;

@RestController
@RequestMapping(value = "/api")
public class UserApiController {
    private final UserService userService;

    @Autowired
    public UserApiController(UserService userService) {
        this.userService = userService;
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User loginUser(@RequestParam(value = "email", required = true) String email, @RequestParam(value = "haslo", required = true) String password) {
        User user = userService.getUserByEmailAndPassword(email, password);
        return user;
    }
    
    @RequestMapping(value = "/users/getall")
    public List<User> listUsers() {
        List<User> users = userService.getList();
        return users;
    }
    
    @RequestMapping(value = "/user/{id}/getfriends", method=RequestMethod.GET)
    public List<User> listFriends(@PathVariable("id") Long id) {
        /*User userA = new User("Jan", "Nowak", "email@wp.pl", "123456", false, 0);
        List<Friend> friends = new ArrayList<>();
        friends.add(new Friend(2, 1));
        friends.add(new Friend(3, 0));
        friends.add(new Friend(4, 1));
        userA.setFriends(friends);

        userService.save(userA);*/
        try {
            List<User> users = userService.getUserFriends(id);
            return users;
        } catch(Exception ex) {
            return null;
        }

    }
    
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public boolean registerUser(@RequestParam(value = "email", required = true) String email, @RequestParam(value = "haslo", required = true) String password) {
        User user = new User(email, password, false, 0);

            User save = userService.save(user);
            return true;
  
    }
    

    
    
}
