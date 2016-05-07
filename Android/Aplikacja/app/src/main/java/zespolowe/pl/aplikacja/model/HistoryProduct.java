package zespolowe.pl.aplikacja.model;

import java.util.Date;
import java.util.List;

/**
 * Created by Piotr on 2016-05-07.
 */
public class HistoryProduct {
    private Product product;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    private User owner;
    private List<User> users;
    private Date date;

    public HistoryProduct() {
    }

    public HistoryProduct(Product product, Date date) {
        this.product = product;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
