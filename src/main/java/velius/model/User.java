/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Klasa mapująca encję z bazy danych dotyczącą użytkownika na obiekt.
 */
@Entity
@Table(name = "uzytkownicy")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name = "imie")
    private String name;
    
    @Column(name = "nazwisko")
    private String surname;
    
    @Column
    @Email
    @NotEmpty
    private String email;
    
    @Column(name = "haslo")
    @NotEmpty
    @JsonIgnore
    private String password;
    
    @JsonIgnore
    @Column(name = "zdjecie", nullable = true, columnDefinition="longblob")
    private byte[] image;
    
    @Column(name = "aktywny")
    private boolean active;
    
    @Column(name = "uprawnienia")
    private int role;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="id_uzytkownika")
    @JsonIgnore
    private List<Friend> friends;
    
 
    @ManyToMany
    @JoinTable(name = "user_product", 
            joinColumns = @JoinColumn(name = "userID"), 
            inverseJoinColumns = @JoinColumn(name = "productID"))
    @JsonIgnore
    private List<Product> products;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_product_history", 
            joinColumns = @JoinColumn(name = "userID"), 
            inverseJoinColumns = @JoinColumn(name = "productHistoryID"))
    @JsonIgnore
    private List<Product> productsHistory;

    public User() {
    }
    
    public User(String name, String surname, String email, String password, boolean active, int role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.active = active;
        this.role = role;
    }

    public User(String email, String password, boolean active, int role) {
        this.email = email;
        this.password = password;
        this.active = active;
        this.role = role;
    }
        
    public long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getRole() {
        return role;
    }
    
    public void setRole(int role) {
        this.role = role;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    /**
     * @return the products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * @param products the products to set
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * @return the productsHistory
     */
    public List<Product> getProductsHistory() {
        return productsHistory;
    }

    /**
     * @param productsHistory the productsHistory to set
     */
    public void setProductsHistory(List<Product> productsHistory) {
        this.productsHistory = productsHistory;
    }
    
    
    
    
    
    
}
