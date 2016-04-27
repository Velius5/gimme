
package velius.model;

import java.util.Date;

/**
 *
 * @author Piotr Czarny
 */
public class ModelProductHistory {
    private Product product;
    private Date date;

    /**
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
