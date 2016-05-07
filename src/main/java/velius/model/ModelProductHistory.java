
package velius.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Piotr Czarny
 */
public class ModelProductHistory {
    private Product product;
    private User owner;
    private List<User> users;
    private List<User> paidUsers;
    private Date date;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

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

    public List<User> getPaidUsers() {
        return paidUsers;
    }

    public void setPaidUsers(List<User> paidUsers) {
        this.paidUsers = paidUsers;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
}
