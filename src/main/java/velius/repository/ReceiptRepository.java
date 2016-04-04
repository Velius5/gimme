/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import velius.model.Receipt;

/**
 *
 * @author Patryk
 */
public interface ReceiptRepository extends JpaRepository<Receipt, String> {
    Receipt findById(long id);
}
