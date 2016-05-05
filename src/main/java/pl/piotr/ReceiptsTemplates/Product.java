/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.piotr.ReceiptsTemplates;

/**
 * Klasa dostarczająca informacji o pojedynczym produkcie.
 * Zawiera pola przechowujące informację o nazwie produktu, jego cenie oraz ilości
 * a także metody pozwalające na ustawienie i pobranie zawartości tych poł
 * @author Piotr Czarny
 */
public class Product {
    /**
     * Pole zawierające nazwę produktu
     */
    protected String name;
    
    /**
     * Pole zawierające cenę produktu
     */
    protected float price;
    
    /**
     * Pole określające ilość
     */
    protected float count;
    
    public Product(){};
    
    public Product(String name, float price, float count){
        this.name = name;
        this.count = count;
        this.price = price;
    }

    /**
     * @return nazwę prouktu
     */
    public String getName() {
        return name;
    }

    /**
     * @return cenę produktu
     */
    public float getPrice() {
        return price;
    }

    /**
     * @return ilość
     */
    public float getCount() {
        return count;
    }

    /**
     * @param name nazwa produktu
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param price cena produktu
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @param count ilość
     */
    public void setCount(float count) {
        this.count = count;
    }
    
    
}
