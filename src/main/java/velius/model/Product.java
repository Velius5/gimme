/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package velius.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Piotr
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
    private double price;
    
    @Column(name="ilosc")
    private double count;
    
    
    @ManyToOne
    @JoinColumn(name="owner_id")
    private User owner;
    
    
    public Product() {
    }
    
    public Product(String productName, double price, double count) {
        this.productName = productName;
        this.price = price;
        this.count = count;
    }
        
    public Product(String productName, double price, double count, User owner) {
        this.productName = productName;
        this.price = price;
        this.count = count;
        this.owner = owner;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", productName=" + productName + ", price=" + price + ", count=" + count + ", ownerId=" + owner + '}';
    }


    
   
    
}
