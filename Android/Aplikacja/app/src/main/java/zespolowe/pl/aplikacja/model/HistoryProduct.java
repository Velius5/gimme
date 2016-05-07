package zespolowe.pl.aplikacja.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Piotr on 2016-05-07.
 */
public class HistoryProduct implements Serializable{
    private Product product;
    private User owner;
    private List<User> users;
    private  List<User> paidUsers;
    private Date date;

    public HistoryProduct(){
    }

    public HistoryProduct(Product product, Date date) {
        this.setProduct(product);
        this.setDate(date);
    }

    public HistoryProduct(Date date, User owner, List<User> paidUsers, Product product, List<User> users) {
        this.date = date;
        this.owner = owner;
        this.paidUsers = paidUsers;
        this.product = product;
        this.users = users;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getPaidUsers() {
        return paidUsers;
    }

    public void setPaidUsers(List<User> paidUsers) {
        this.paidUsers = paidUsers;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
