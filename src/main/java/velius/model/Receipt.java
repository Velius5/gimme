/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package velius.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import org.hibernate.annotations.Cascade;

/**
 * Klasa mapująca encję z bazy danych dotyczącą paragonu na obiekt.
 */
@Entity
@Table(name = "paragony")
public class Receipt {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="data_zakupu")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    
    @Column(name="nazwa_sklepu")
    private String name;
    
    @Column(name="zdjecie", columnDefinition="longblob")
    @JsonIgnore
    private byte[] image;
    
    @Column(name="opis")
    private String description;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "receipt")
    @JsonManagedReference
    private List<Product> productList;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="owner_id")
    private User owner;
    
    @Column(name="suma")
    private BigDecimal sum;

    public Receipt(){};
    
    public Receipt(byte[] image) throws IOException {
        this.image = image;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the image
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param image the image to set
     */
    public void setImage(byte[] image) {
        this.image = image;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * @return the sum
     */
    public BigDecimal getSum() {
        return sum;
    }

    /**
     * @param summary the sum to set
     */
    public void setSum(BigDecimal summary) {
        this.sum = summary;
    }
    
    
}
