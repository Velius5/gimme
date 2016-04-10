/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import velius.model.User;
import velius.service.UserService;


@Controller
public class PanelController {
    
    @Autowired
    UserService userService;
    
    @RequestMapping("/panel")
    public String panelPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        
        User user = userService.getUserByEmail(email);
        
        model.addAttribute("pageMessage", user.getSurname());
        return "panel";
    }
}
