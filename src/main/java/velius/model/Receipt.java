/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package velius.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
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
import net.coobird.thumbnailator.Thumbnails;

/**
 * Klasa mapująca encję z bazy danych dotyczącą paragonu na obiekt.
 */
@Entity
@Table(name = "paragony")
public class Receipt{

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
    
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name="owner_id")
    private User owner;
    
    @Column(name="suma")
    private BigDecimal sum;

    public Receipt(){};
    
    public Receipt(pl.piotr.ReceiptsTemplates.Receipt tempReceipt,byte[] image, User owner) throws IOException{
        BufferedImage temp = ImageIO.read(new ByteArrayInputStream(image));
        BufferedImage img = Thumbnails.of(temp).size(640, 1138).asBufferedImage();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", baos);
        byte[] bytes = baos.toByteArray();
        this.image = bytes;
        
        this.date = tempReceipt.getDate();
        this.name = tempReceipt.getShopName();
        this.owner = owner;
        this.sum = BigDecimal.valueOf(tempReceipt.getSum());
        productList = new ArrayList<>();
        for(pl.piotr.ReceiptsTemplates.Product product : tempReceipt.getProductList()) {
            Product prod = new Product(product.getName(), BigDecimal.valueOf(product.getPrice()), (double)product.getCount(), 
            owner,this);
            if(prod != null) 
            productList.add(prod);
        }
    }
    
    public Receipt(byte[] image) throws IOException {
        BufferedImage temp = ImageIO.read(new ByteArrayInputStream(image));
        BufferedImage img = Thumbnails.of(temp).size(640, 1138).asBufferedImage();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", baos);
        byte[] bytes = baos.toByteArray();
        this.image = bytes;
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
    public void setImage(byte[] image) throws IOException {
        BufferedImage temp = ImageIO.read(new ByteArrayInputStream(image));
        BufferedImage img = Thumbnails.of(temp).size(640, 1138).asBufferedImage();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", baos);
        byte[] bytes = baos.toByteArray();
        this.image = bytes;
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
    
    public void setProductPricePerPerson(){
        for(Product prod : this.productList)
            prod.setPricePerPerson();
    }
    
    
}
