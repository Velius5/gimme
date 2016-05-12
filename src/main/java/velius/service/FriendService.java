/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.service;

import java.util.List;
import velius.model.Friend;
import velius.model.User;


public interface FriendService {
    Friend save(Friend friend);
    List<Friend>  getUserFriendsByFriendId(User user);
    List<Friend>  getUserFriendsByUserId(User user);
    Friend getFriend(User user,User friend);
    void deleteFriend(Friend friend);
    void acceptFriend(User user, User friend);
}
