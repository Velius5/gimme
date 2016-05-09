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
import org.springframework.web.bind.annotation.RestController;
import velius.model.Notification;
import velius.model.User;
import velius.service.NotificationService;
import velius.service.UserService;

@RestController
@RequestMapping(value = "/api/notifications")
public class NotificationApiController {
    @Autowired
    UserService userService;
    
    @Autowired
    NotificationService notificationService;
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Notification getLastNotificationByUserId(@PathVariable("id") Long id){
        User user = userService.getUser(id);
        if(user != null) {
            Notification no = notificationService.getLastNotificationByUserId(id);
            if(no != null) {
                no.setRead(true);
                notificationService.save(no);
                return no;
            } else {
                return null;
            }
        } else {
            return null;
        }
        
    }
}
