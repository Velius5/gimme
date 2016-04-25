/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.piotr;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.*;
import org.apache.commons.lang3.StringUtils;
import pl.piotr.ReceiptsTemplates.Biedronka;
import pl.piotr.ReceiptsTemplates.Lidl;
import pl.piotr.ReceiptsTemplates.Receipt;
import pl.piotr.ReceiptsTemplates.Tesco;
import pl.piotr.ReceiptsTemplates.Zabka;

/**
 * Główna klasa modułu aplikacji odpowiedzialnego za rozpoznawanie tekstu.
 * Pozwala na użycie silnika Tesseract OCR.
 * @author Piotr
 */
public class TessOCR extends ReceiptParser{
    private static ArrayList<String> shopHeaderList;
    private static ITesseract ocr = new Tesseract();
    
    
    /**
     * Funkcja służąca do rozpoznawania tekstu zawartego na zdjęciu paragonu.
     * Funkcja rozpoznaje z jakiego sklepu pochodzi paragon i zwraca obiekt
     * odpowiedniej klasy.
     * @param image zdjęcie paragonu w formacie JPG
     * @return obiekt klasy implementującej klase abstrakcyjną pl.piotr.Receipt
     */
    public static Receipt recognizeReceipt(byte[] image){
        ocr.setLanguage("pol");
        Receipt receipt = null;
        try {
            InputStream in = new ByteArrayInputStream(image);
            BufferedImage img = ImageIO.read(in);
            ocr.setLanguage("pol");
            String text = ocr.doOCR(img).toUpperCase();
            return parseString(text);
        } catch (TesseractException ex) {
            Logger.getLogger(TessOCR.class.getName()).log(Level.SEVERE, null, ex);
        }catch(IOException e){
            e.printStackTrace();
        }
        return receipt;
    }
}
