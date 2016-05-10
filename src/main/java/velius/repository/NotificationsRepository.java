/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import velius.model.Notification;

/**
 *
 * @author Patryk
 */
public interface NotificationsRepository extends JpaRepository<Notification, String> {
    Notification findTop1ByUseridAndReadOrderById(Long userid, Boolean read);
    List<Notification> findAllByUseridOrderByDateDesc(Long userid);
}
