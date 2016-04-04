/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.piotr;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.tess4j.*;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Piotr
 */
public class TessOCR {
    private static ArrayList<String> shopHeaderList;
    private static ITesseract ocr;
    
    /**
     *
     */
    public static void init(){
        ocr = new Tesseract();
        shopHeaderList = new ArrayList<>();
        shopHeaderList.add("BIEDRONKA \"CODZIENNIE NISKIE CENY\"");
        shopHeaderList.add("LIDL POLSKA SKLEPY SPOZYWCZE");
        shopHeaderList.add("TESCO /POLSKA/ SP Z.O.O");
        shopHeaderList.add("SKLEP ZABKA");
    }
    

    public static Receipt recognizeReceipt(BufferedImage img){
        int minEditLength = 100;
        Receipt receipt = null;
        try {
            ocr.setLanguage("pol");
            String text = ocr.doOCR(img).toUpperCase();
            System.out.println(text);
            Scanner scaner = new Scanner(text);
            String line = scaner.nextLine();
            
            int tmp=0;
            int LD;
            for(int i=0;i<shopHeaderList.size();i++){
                LD = StringUtils.getLevenshteinDistance(line, shopHeaderList.get(i));
                if(LD < minEditLength){
                    minEditLength = LD;
                    tmp = i;
                }
                //System.out.println(LD);
            }
            //System.out.println(tmp);
            switch(tmp){
                case 0:
                    receipt = new Biedronka();
                    break;
                case 1:
                    receipt = new Lidl();
                    break;
                case 2:
                    receipt = new Tesco();
                    break;
                case 3:
                    receipt = new Zabka();
                    break;
            }
            
            receipt.setDate(text);
            receipt.setProductList(text);
            receipt.setSum(text);
            
            
        } catch (TesseractException ex) {
            Logger.getLogger(TessOCR.class.getName()).log(Level.SEVERE, null, ex);
        }
        return receipt;
    }
    /*
    public static void main(String[] args){
        TessOCR.init();
        File img = new File("bieda0.tif");
        Receipt recognizeReceipt = TessOCR.recognizeReceipt(img);
    }
    */

}
