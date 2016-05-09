/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.service;

import java.util.List;
import velius.model.Notification;
import velius.model.User;

/**
 *
 * @author Patryk
 */
public interface NotificationService {
    Notification save(Notification notification);
    Notification getLastNotificationByUserId(Long userid);
    List<Notification> getNotificationsHistoryByUserId(Long userid);
    void setNotificationAsRead(Notification notification);
}