/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.piotr;

import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author Piotr
 */
public abstract class Receipt {
    protected Date date;
    protected ArrayList<Product> productList;
    protected float sum;
    protected String shopName;
    
    public abstract void setDate(String txt);
    public abstract void setProductList(String txt);
    public abstract void setSum(String txt);

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return the productList
     */
    public ArrayList<Product> getProductList() {
        return productList;
    }

    /**
     * @return the sum
     */
    public float getSum() {
        return sum;
    }

    public String getShopName() {
        return shopName;
    }
    
    
    
}
