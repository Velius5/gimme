/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.service;

import java.util.List;
import velius.model.Receipt;
import velius.model.User;

/**
 *
 * @author Patryk
 */
public interface ReceiptService {
    Receipt save(Receipt receipt);
    List<Receipt> findAllByOwner(User user);
}
