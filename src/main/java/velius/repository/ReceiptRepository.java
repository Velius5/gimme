/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import velius.model.Receipt;
import velius.model.User;

/**
 *
 * @author Patryk
 */
public interface ReceiptRepository extends JpaRepository<Receipt, String> {
    Receipt findById(long id);
    List<Receipt> findAllByOwner(User user);

    public List<Receipt> findFirst6ByOwnerOrderByIdDesc(User user);

}
