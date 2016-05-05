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
import velius.model.Friend;
import velius.model.User;
import velius.repository.FriendRepository;

/**
 *
 * @author Patryk
 */
@Service
public class FriendServiceImpl implements FriendService {
    
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(UserServiceImpl.class);
    private final FriendRepository repository;

    @Autowired
    public FriendServiceImpl(FriendRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Friend> getUserFriendsByFriendId(User user) {
        return repository.findAllByFriendId(user);
    }

    @Override
    public List<Friend> getUserFriendsByUserId(User user) {
        return repository.findAllByUserId(user);
    }

    @Override
    public Friend save(Friend friend) {
        return repository.save(friend);
    }
    
}
