/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.service;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import velius.model.Receipt;
import velius.model.User;
import velius.repository.ReceiptRepository;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final ReceiptRepository repository;

    @Autowired
    public ReceiptServiceImpl(ReceiptRepository repository) {
        this.repository = repository;
    }

    


    @Transactional
    public Receipt save(@NotNull @Valid final Receipt receipt) {
        LOGGER.debug("Creating {}", receipt);
        return repository.save(receipt);
    }

    @Override
    public List<Receipt> findAllByOwner(User user) {
        return repository.findAllByOwner(user);
    }

    @Override
    public List<Receipt> findLast6ByOwner(User user) {
        List<Receipt> receipts = repository.findFirst6ByOwnerOrderByIdDesc(user);
        return receipts;
    }
    
}
