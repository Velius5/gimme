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
import velius.model.Product;
import velius.model.User;
import velius.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
    
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(UserServiceImpl.class);
    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Product save(@NotNull @Valid final Product product) {
        LOGGER.debug("Creating {}", product);
        return repository.save(product);
    }

    @Override
    public List<Product> getUserDebitors(User user) {
        return repository.findIdDistinctByOwnerAndUsersNotNull(user);
    }

    @Override
    public List<Product> getMyDebts(User user) {
        return repository.findAllByUsers(user);
    }

    @Override
    public List<Product> getMyDebtsToFriend(User user, User friend) {
        return repository.findIdDistinctByOwnerAndUsers(friend,user);
    }

    @Override
    public List<Product> getFriendDebtsToMe(User user, User friend) {
        return repository.findIdDistinctByOwnerAndUsers(user, friend);
    }

    @Override
    public Product getProduct(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Product> getUserDebitorsHistory(User user) {
        return repository.findIdDistinctByOwnerAndUsersHistoryNotNull(user);
    }

    @Override
    public List<Product> getMyDebtsHistory(User user) {
        return repository.findAllByUsersHistory(user);
    }
    
}
