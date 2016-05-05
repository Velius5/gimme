package velius.controller.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import velius.model.Friend;
import velius.model.ModelFriend;
import velius.model.Product;
import velius.model.Response;
import velius.model.User;
import velius.service.FriendService;
import velius.service.ProductService;
import velius.service.UserService;

@RestController
@RequestMapping(value = "/api")
public class UserApiController {

    private final UserService userService;
    private final ProductService productService;
    private final FriendService friendService;

    @Autowired
    public UserApiController(UserService userService, ProductService productService, FriendService friendService) {
        this.userService = userService;
        this.productService = productService;
        this.friendService = friendService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User loginUser(@RequestParam(value = "email", required = true) String email, @RequestParam(value = "haslo", required = true) String password) {
        User user = userService.getUserByEmailAndPassword(email, password);
        return user;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") Long id) {
        User user = userService.getUser(id);
        return user;
    }

    @RequestMapping(value = "/user/search/{id}/{fullname}", method = RequestMethod.GET)
    public List<User> getUserByFullname(@PathVariable("id") Long id, @PathVariable("fullname") String fullname) {
        User user = userService.getUser(id);
        List<Friend> userFriendsByFriendId = friendService.getUserFriendsByFriendId(user);
        List<Friend> userFriendsByUserId = friendService.getUserFriendsByUserId(user);
        
        List<User> userFriendsOne = new ArrayList<>();
        List<User> userFriendsTwo = new ArrayList<>();
        for(Friend f : userFriendsByFriendId) {
            User us = userService.getUser(f.getUserId());
            userFriendsOne.add(us);
        }
        for(Friend f : userFriendsByUserId) {
            User usr = userService.getUser(f.getFriendId());
            userFriendsTwo.add(usr);
        }
        
        List<User> usersList = userService.getUsersByFullnameLike(fullname);
        List<User> selectedUsers = new ArrayList<>();
        for (User friend : usersList) {
            if (friend.getId() != id && !userFriendsOne.contains(friend) && !userFriendsTwo.contains(friend)) {
                selectedUsers.add(friend);
            }
        }
        return selectedUsers;
    }

    @RequestMapping(value = "/users/getall")
    public List<User> listUsers() {
        List<User> users = userService.getList();
        return users;
    }

    @RequestMapping(value = "/user/{id}/getfriends", method = RequestMethod.GET)
    public List<ModelFriend> listFriends(@PathVariable("id") Long id) {
        User user = userService.getUser(id);

        List<User> friends = userService.getUserFriends(user.getId());
        List<ModelFriend> friendList = new ArrayList();
        for (User friend : friends) {
            ModelFriend mf = new ModelFriend();
            mf.setId(friend.getId());
            mf.setName(friend.getName());
            mf.setSurname(friend.getSurname());

            
            BigDecimal bilans =  BigDecimal.ZERO;

            List<Product> temp = productService.getMyDebtsToFriend(user, friend);
            for (Product prod : temp) {
                bilans = bilans.subtract(prod.getPricePerPerson());
            }

            temp = productService.getFriendDebtsToMe(user, friend);
            for (Product prod : temp) {
                bilans = bilans.add(prod.getPricePerPerson());
            }
            mf.setBilans(bilans);
            friendList.add(mf);
        }
        Collections.sort(friendList);
        return friendList;
    }

    @RequestMapping(value = "/user/{id}/addfriend/{friendId}", method = RequestMethod.GET)
    public Response addUserFriends(@PathVariable("id") Long id, @PathVariable("friendId") Long friendId) {

        User user = userService.getUser(id);
        User friend = userService.getUser(friendId);
        List<User> friends = userService.getUserFriends(user.getId());
        List<User> invitations = userService.getUserInvitations(user.getId());

        if (friendId == user.getId() || friends.contains(friend) || invitations.contains(friend)) {
            return new Response(false);
        } else {
            List<Friend> friendList = user.getFriends();
            Friend fr = new Friend(user, friend, 1);
            friendList.add(new Friend(user, friend, 1));
            user.setFriends(friendList);
            userService.save(user);
            return new Response(true);
        }

    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public Response registerUser(@RequestParam(value = "email", required = true) String email, @RequestParam(value = "haslo", required = true) String password) {

        User user = new User(email, password, false, 0);
        User save = userService.save(user);
        Response response = new Response(true);
        return response;

    }

}
