/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Klasa mapująca encję z bazy danych dotyczącą znajomych na obiekt.
 */
@Entity
@Table(name = "znajomi")
public class Friend {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name = "id_znajomego")
    private long friendId;
    
    @Column(name = "status")
    private long status;

    public Friend(){}

    public Friend(long friendId, long status) {
        this.friendId = friendId;
        this.status = status;
    }

    public long getFriendId() {
        return friendId;
    }

    public void setFriendId(long friendId) {
        this.friendId = friendId;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }
    
    

}
