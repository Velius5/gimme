/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package velius.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Klasa mapująca encję z bazy danych dotyczącą produktu na obiekt.
 */
@Entity
@Table(name = "produkty")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="nazwa_produktu")
    private String productName;
    
    @Column(name="cena")
    private BigDecimal price;
    
    @Column(name="ilosc")
    private double count;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="owner_id")
    @JsonBackReference
    private User owner;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="receiptID")
    @JsonBackReference
    private Receipt receipt;
    
    @ManyToMany
    @JoinTable(name = "user_product", 
            joinColumns = @JoinColumn(name = "productID"), 
            inverseJoinColumns = @JoinColumn(name = "userID"))
    @JsonBackReference
    private List<User> users;
    
    @ManyToMany(mappedBy = "productsHistory")
    @JsonBackReference
    private List<User> usersHistory;
    
    
    public Product() {
    }
    
    public Product(String productName, BigDecimal price, double count) {
        this.productName = productName;
        this.price = price;
        this.count = count;
    }
        
    public Product(String productName, BigDecimal price, double count, User owner, Receipt receipt) {
        this.productName = productName;
        this.price = price;
        this.count = count;
        this.owner = owner;
        this.receipt = receipt;
    }

    public Long getId() {
        return id;
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

    public double getCount() {
        return count;
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
    
    @Transient
    @JsonIgnore
    public BigDecimal getPricePerPerson(){
        return BigDecimal.valueOf((price.doubleValue()*count)/((1.0)*(users.size()+1))).setScale(2, RoundingMode.FLOOR);
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", productName=" + productName + ", price=" + price + ", count=" + count + ", ownerId=" + owner + '}';
    }

    /**
     * @return the receipt
     */
    public Receipt getReceipt() {
        return receipt;
    }

    /**
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * @param receipt the receipt to set
     */
    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }


    
   
    
}
