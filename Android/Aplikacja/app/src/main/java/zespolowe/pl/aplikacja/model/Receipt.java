package zespolowe.pl.aplikacja.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Receipt implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String date;
    private String name;
    private byte[] image;
    private String description;
    private List<Product> productList;
    private User owner;
    private BigDecimal sum;

    public Receipt() {
    }



    public Receipt(List<Product> productList, Long id, String date, String name, String description, User owner, BigDecimal sum) {
        this.productList = productList;
        this.id = id;
        this.date = date;
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.sum = sum;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", productList=" + productList +
                ", owner=" + owner +
                ", sum=" + sum +
                '}';
    }
}
