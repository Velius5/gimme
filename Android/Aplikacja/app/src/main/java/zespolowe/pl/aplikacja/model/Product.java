package zespolowe.pl.aplikacja.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String productName;
    private BigDecimal price;
    private double count;
    private User owner;
    private Receipt receipt;
    private List<User> users;
    private List<User> usersHistory;

    public Product() {
    }

    public Product(Long id, String productName, BigDecimal price, double count, User owner, Receipt receipt, List<User> users) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.count = count;
        this.owner = owner;
        this.receipt = receipt;
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCount() {
        return String.valueOf(count);
    }

    public void setCount(double count) {
        this.count = count;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public List<User> getUsersHistory() {
        return usersHistory;
    }

    public void setUsersHistory(List<User> usersHistory) {
        this.usersHistory = usersHistory;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", owner=" + owner +
                ", receipt=" + receipt +
                ", users=" + users +
                '}';
    }
}