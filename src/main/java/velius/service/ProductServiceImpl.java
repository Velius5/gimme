<<<<<<< HEAD
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
        return repository.findAllByOwner(user);
    }

    @Override
    public List<Product> getMyDebts(User user) {
        return repository.findAllByUsers(user);
    }
    
}
=======
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
    
}
>>>>>>> cbf69cf021552409f2e3d375a47d9da42d236b43
