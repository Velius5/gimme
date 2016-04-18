/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.piotr;

import java.util.Date;
import java.util.ArrayList;

/**
 * Klasa abstrakcyjna pełniąca role szablonu paragonu.
 * Deklaruje zawieranie przez klasy implementujące pól takich jak data zakupu,
 * lista produktów, suma, nazwa sklepu oraz metod pozwalających na ustawienie
 * tych pól oraz pobranie ich wartości
 * @author Piotr Czarny
 */
public abstract class Receipt {
    /**
     * Pole przechowujące date dokonania zakupu
     */
    protected Date date;
    
    /**
     * Pole przechowujące listę produktów
     */
    protected ArrayList<Product> productList;
    
    /**
     * Pole przechowujące sumę na którą dokonano zakupów
     */
    protected float sum;
    
    /**
     * Pole przechowujące nazwe sklepu
     */
    protected String shopName;
    
    /**
     * Funkcja wyodrębniająca z paragonu date dokonania zakupu
     * @param txt tekst rozpoznany przez silnik OCR
     */
    public abstract void setDate(String txt);
    
    /**
     * Funkcja wyodrębniająca z paragonu listę produktów
     * @param txt tekst rozpoznany przez silnik OCR
     */
    public abstract void setProductList(String txt);
    
    /**
     * Funkcja wyodrębniająca z paragonu sumę dokonanych zakupów
     * @param txt tekst rozpoznany przez silnik OCR
     */
    public abstract void setSum(String txt);

    /**
     * Funkcja zwraca przechowywaną datę
     * @return java.util.Date 
     */
    public Date getDate() {
        return date;
    }

    /**
     * Funkcja zwraca przechowywaną listę produktów
     * @return the productList
     */
    public ArrayList<Product> getProductList() {
        return productList;
    }

    /**
     * Funkcja zwraca przechowywaną sumę zakupów
     * @return the sum
     */
    public float getSum() {
        return sum;
    }

    /**
     * Funkcja zwraca nazwę sklepu
     * @return shopName
     */
    public String getShopName() {
        return shopName;
    }
    
    
    
}
