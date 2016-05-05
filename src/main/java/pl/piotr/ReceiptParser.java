
package pl.piotr;

import java.util.ArrayList;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;
import pl.piotr.ReceiptsTemplates.Biedronka;
import pl.piotr.ReceiptsTemplates.Lidl;
import pl.piotr.ReceiptsTemplates.Receipt;
import pl.piotr.ReceiptsTemplates.Tesco;
import pl.piotr.ReceiptsTemplates.Zabka;

/**
 * Klasa dostarcza pola i metody wspólne dla obu silników OCR
 * @author Piotr Czarny
 */
public class ReceiptParser {
    /**
     * Statyczna tablica przechowująca nagłówki rozpoznawancyh paragonów 
     */
    protected static ArrayList<String> shopHeaderList;
    
    /**
     *  Funkcja inicjująca statyczną tablicę shopHeaderList
     * z nazwami sklepów oraz ustawiająca.
     */
    public static void init(){
        shopHeaderList = new ArrayList<>();
        shopHeaderList.add("BIEDRONKA \"CODZIENNIE NISKIE CENY\"");
        shopHeaderList.add("LIDL POLSKA SKLEPY SPOZYWCZE");
        shopHeaderList.add("TESCO /POLSKA/ SP Z.O.O");
        shopHeaderList.add("SKLEP ZABKA");
    }

    /**
     * Metoda rozpoznaje sklep z jakiego pochodzi paragon, tworzy obiekt odpowiedniej klasy
     * i przekazuje tekst z paragonu w celu dalszego przetworzenia
     * @param text - tekst rozpoznany ze zdjęcia paragonu przez silnik OCR
     * @return obiekt klasy pl.piotr.receiptTemplates.Receipt zawierający dane
     * z paragonu
     */
    protected static Receipt parseString(String text){
        System.out.println(text);
        String trimmedText = new String();

        int minEditLength = 100;
        Receipt receipt = null;

        Scanner scaner = new Scanner(text);
        String line = scaner.nextLine().trim();
        
        int tmp = 0;
        int LD;
        for (int i = 0; i < shopHeaderList.size(); i++) {
            LD = StringUtils.getLevenshteinDistance(line, shopHeaderList.get(i));
            if (LD < minEditLength) {
                minEditLength = LD;
                tmp = i;
            }
        }
        switch (tmp) {
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

        scaner = new Scanner(text);
        while(scaner.hasNextLine())
            trimmedText = trimmedText.concat(scaner.nextLine().trim() + "\n");
        text = trimmedText;
        System.out.println(text);
        receipt.setDate(text);
        receipt.setProductList(text);
        receipt.setSum(text);

        return receipt;
    }
}
