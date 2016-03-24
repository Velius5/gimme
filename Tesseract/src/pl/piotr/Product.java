/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.piotr;

/**
 *
 * @author Piotr
 */
public class Product {
    protected String name;
    protected float price;
    protected float count;
    
    public Product(){};
    
    public Product(String name, float price, float count){
        this.name = name;
        this.count = count;
        this.price = price;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @return the count
     */
    public float getCount() {
        return count;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @param count the count to set
     */
    public void setCount(float count) {
        this.count = count;
    }
    
    
}
