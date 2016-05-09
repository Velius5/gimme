/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Patryk
 */
@Entity
@Table(name = "powiadomienia")
public class Notification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="typ")
    String type;
    
    @Column(name="data")
    @Temporal(javax.persistence.TemporalType.DATE)
    Date date;
    
    @Column(name="wiadomosc")
    String message;
    
    @Column(name="przeczytano")
    Boolean read;
    
    @Column(name="id_uzytkownika")
    Long userid;

    public Notification() {
    }

    public Notification(String type, Date date, String message, Boolean read, Long userid) {
        this.type = type;
        this.date = date;
        this.message = message;
        this.read = read;
        this.userid = userid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
    
    
    
}
