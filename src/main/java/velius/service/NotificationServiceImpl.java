/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import velius.model.Notification;
import velius.model.User;
import velius.repository.NotificationsRepository;

/**
 *
 * @author Patryk
 */
@Service
public class NotificationServiceImpl implements NotificationService {
    
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(UserServiceImpl.class);
    private final NotificationsRepository repository;

    @Autowired
    public NotificationServiceImpl(NotificationsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Notification save(Notification notification) {
        return repository.save(notification);
    }

    @Override
    public Notification getLastNotificationByUserId(Long userid) {
        return repository.findTop1ByUseridAndReadOrderById(userid, false);
    }

    @Override
    public List<Notification> getNotificationsHistoryByUserId(Long userid) {
        return repository.findAllByUserid(userid);
    }

    @Override
    public void setNotificationAsRead(Notification notification) {
        Notification notif = repository.findOne(notification.getId().toString());
        notif.setRead(true);
        repository.save(notif);
    }
    
}
