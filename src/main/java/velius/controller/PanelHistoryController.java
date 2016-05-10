
package velius.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import velius.model.ModelProductHistory;
import velius.model.Notification;
import velius.model.Product;
import velius.model.User;
import velius.service.NotificationService;
import velius.service.ProductService;
import velius.service.UserService;

/**
 *
 * @author Piotr Czarny
 */

@Controller
@RequestMapping("/panel/history")
public class PanelHistoryController {
    @Autowired
    UserService userService;
    
    @Autowired
    NotificationService notificationService;
   
    
    @RequestMapping("")
    public String historyPage(Model model,Principal principal){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userService.getUserByEmail(email);

        if(user != null) {
            List<Notification> userNotifications = notificationService.getNotificationsHistoryByUserId(user.getId());
            model.addAttribute("userNotifications", userNotifications);
        }
        
        return "panel_history";
    }
}
