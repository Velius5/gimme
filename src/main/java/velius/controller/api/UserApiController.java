
package velius.controller.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public User loginUser(@RequestParam(value = "email", required = false) String email, @RequestParam(value = "haslo", required = false) String password) {
        User user = userService.getUserByEmailAndPassword(email, password);
        return user;
    }
    
    @RequestMapping(value = "/getall")
    public List<User> listUsers() {
        List<User> users = userService.getList();
        return users;
    }
    

    
    
}
