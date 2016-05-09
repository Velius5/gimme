package zespolowe.pl.aplikacja.model;

import java.util.Date;

/**
 * Created by Patryk on 2016-05-07.
 */
public class Notification {

    Long id;
    String type;
    Long userid;
    String date;
    String message;
    Boolean read;

    public Notification() {
    }

    public Notification(String type, Long userid, String date, String message, Boolean read) {
        this.type = type;
        this.userid = userid;
        this.date = date;
        this.message = message;
        this.read = read;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", userid=" + userid +
                ", date=" + date +
                ", message='" + message + '\'' +
                ", read=" + read +
                '}';
    }
}
