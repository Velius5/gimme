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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
    
    @ManyToOne
    @JoinColumn(name ="userId")
    User userId;
    
    @OneToOne
    private User friendId;
    
    @Column(name = "status")
    private long status;

    public Friend(){}

    public Friend(User friendId, long status) {
        this.friendId = friendId;
        this.status = status;
    }
    
    public Friend(User user,User friendId, long status) {
        this.userId = user;
        this.friendId = friendId;
        this.status = status;
    }

    public Long getFriendId() {
        return friendId.getId();
    }

    public void setFriendId(User friendId) {
        this.friendId = friendId;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }
    
    

}
