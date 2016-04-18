/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import velius.model.Response;
import velius.model.User;
import velius.service.UserService;

@RestController
@RequestMapping(value = "/api")
public class UserEditProfileApiController {
    private final UserService userService;
    
    @Autowired
    public UserEditProfileApiController(UserService userService) {
        this.userService = userService;
    }
    
    @RequestMapping(value = "/user/{id}/editProfile", method = RequestMethod.POST)
    public Response editUserProfile(@PathVariable("id") Long id, @RequestParam(value = "name", required = true) String name, @RequestParam(value = "surname", required = true) String surname, @RequestParam(value = "image", required = true) byte[] image, @RequestParam(value = "email", required = true) String email, @RequestParam(value = "password", required = true) String password) {
        
        User user = userService.getUser(id);
        if(!(user == null)) {
            if(userService.getUserByEmail(email) == null) user.setEmail(email);
            user.setImage(image);
            user.setName(name);
            user.setPassword(password);
            user.setSurname(surname);

            User save = userService.save(user);
            Response response = new Response(true);
            return response;
        } else {
            Response response = new Response(false);
            return response;
        }
        
    }
    
    @RequestMapping(value = "/user/{id}/editPassword", method = RequestMethod.POST)
    public Response editUserPassword(@PathVariable("id") Long id, @RequestParam(value = "password", required = true) String password) {
        
        User user = userService.getUser(id);
        if(!(user == null)) {
            user.setPassword(password);

            User save = userService.save(user);
            Response response = new Response(true);
            return response;
        } else {
            Response response = new Response(false);
            return response;
        }
        
    }
    
    @RequestMapping(value = "/user/{id}/editPhoto", method = RequestMethod.POST)
    public Response editUserPhoto(@PathVariable("id") Long id, @RequestParam(value = "image", required = true) byte[] image) {
        
        User user = userService.getUser(id);
        if(!(user == null)) {
            user.setImage(image);

            User save = userService.save(user);
            Response response = new Response(true);
            return response;
        } else {
            Response response = new Response(false);
            return response;
        }
        
    }
    
    @RequestMapping(value = "/user/{id}/editEmail", method = RequestMethod.POST)
    public Response editUserEmail(@PathVariable("id") Long id, @RequestParam(value = "email", required = true) String email) {
        
        User user = userService.getUser(id);
        if(!(user == null)) {
            if(userService.getUserByEmail(email) == null) {
                user.setEmail(email);
            } else {
                Response response = new Response(false);
                return response;
            }
            User save = userService.save(user);
            Response response = new Response(true);
            return response;
        } else {
            Response response = new Response(false);
            return response;
        }
        
    }
    
    @RequestMapping(value = "/user/{id}/editNameAndUsername", method = RequestMethod.POST)
    public Response editNameAndUsername(@PathVariable("id") Long id, @RequestParam(value = "name", required = true) String name, @RequestParam(value = "surname", required = true) String surname) {
        
        User user = userService.getUser(id);
        if(!(user == null)) {
            user.setName(name);
            user.setSurname(surname);

            User save = userService.save(user);
            Response response = new Response(true);
            return response;
        } else {
            Response response = new Response(false);
            return response;
        }
        
    }        
        

    
}
