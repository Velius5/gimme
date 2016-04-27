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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.*;
import pl.piotr.ReceiptsTemplates.Receipt;

/**
 * Główna klasa modułu aplikacji odpowiedzialnego za rozpoznawanie tekstu.
 * Pozwala na użycie silnika Tesseract OCR.
 * @author Piotr
 */
public class TessOCR extends ReceiptParser{
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
//            MBFImage im = ImageUtilities.createMBFImage(img, true);
//            im.processInplace(new CannyEdgeDetector());
//            DisplayUtilities.display(im);
//            BufferedImage immm = ImageUtilities.createBufferedImageForDisplay(im);
            
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
