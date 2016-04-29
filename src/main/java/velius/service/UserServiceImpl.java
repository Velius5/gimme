/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.service;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import jdk.nashorn.internal.objects.NativeArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import velius.model.Friend;
import velius.model.User;
import velius.repository.FriendRepository;
import velius.repository.UserRepository;

@Service
@Validated
public class UserServiceImpl implements UserService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository repository;
    
    @Autowired
    FriendRepository friendRepository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }
    
    

    @Transactional
    public User save(@NotNull @Valid final User user) {
        LOGGER.debug("Creating {}", user);
        return repository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> getList() {
        LOGGER.debug("Retrieving the list of all users");
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public User getUser(long id) {
        LOGGER.debug("Get user by ID");
        return repository.findById(id);
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        LOGGER.debug("Login user with email " + email);
        return repository.findByEmailAndPassword(email, password);
    }

    @Override
    public List<User> getUserFriends(long id) {
        List<Friend> friends = repository.findById(id).getFriends();
        List<User> users = new ArrayList<>();
        for(Friend friend : friends) {
            if(friend.getStatus() == 1) {
                User user = repository.findById(friend.getFriendId());
                users.add(user);
            }
        }
        List<Friend> friendsInverse = friendRepository.findAllByFriendId(repository.findById(id));
        for(Friend friend : friendsInverse) {
            if(friend.getStatus() == 1) {
                User user = repository.findById(friend.getUserId());
                users.add(user);
            }
        }
        return users;
    }

    
    @Override
    public Boolean exists(long id) {
        return repository.exists(id);
    }

    @Override
    public User getUserByEmail(String email) {
        User user = repository.findByEmail(email);
        return user;
    }

    @Override
    public List<User> getUsersByFullnameLike(String fullname) {
        List<User> usersList;
        String nameAndSurname[] = fullname.split(" ");
        if(nameAndSurname.length < 2)
            usersList = repository.findTop30BySurnameContainingAndNameContaining(nameAndSurname[0], "");
        else {
            usersList = repository.findTop30BySurnameContainingAndNameContaining(nameAndSurname[0], nameAndSurname[1]);
            if(usersList.isEmpty())
            usersList = repository.findTop30BySurnameContainingAndNameContaining(nameAndSurname[1], nameAndSurname[0]);
        }
        
        return usersList;
    }

    @Override
    public List<User> getUserInvitations(long id) {
        List<Friend> friends = repository.findById(id).getFriends();
        List<User> users = new ArrayList<>();
        for(Friend friend : friends) {
            if(friend.getStatus() == 0) {
                User user = repository.findById(friend.getFriendId());
                users.add(user);
            }
        }
        return users;
    }


}
