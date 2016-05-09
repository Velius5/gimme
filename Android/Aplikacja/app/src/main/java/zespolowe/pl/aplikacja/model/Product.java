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
    private BigDecimal pricePerPerson;
    private User owner;
    private Receipt receipt;
    private List<User> users;
    private List<User> usersHistory;

    public Product() {
    }

    public Product(double count, Long id, User owner, BigDecimal price, BigDecimal pricePerPerson, String productName, Receipt receipt, List<User> users, List<User> usersHistory) {
        this.count = count;
        this.id = id;
        this.owner = owner;
        this.price = price;
        this.setPricePerPerson(pricePerPerson);
        this.productName = productName;
        this.receipt = receipt;
        this.users = users;
        this.usersHistory = usersHistory;
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


    public BigDecimal getPricePerPerson() {
        return pricePerPerson;
    }

    public void setPricePerPerson(BigDecimal pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
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