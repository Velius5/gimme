
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
 *
 * @author Piotr Czarny
 */
public class ReceiptParser {
    
    protected static ArrayList<String> shopHeaderList;
    
    /**
     *  Funkcja inicjująca tablicę z nazwami sklepów oraz ustawiająca niektóre
     *  właściwości silnika OCR
     */
    public static void init(){
        shopHeaderList = new ArrayList<>();
        shopHeaderList.add("BIEDRONKA \"CODZIENNIE NISKIE CENY\"");
        shopHeaderList.add("LIDL POLSKA SKLEPY SPOZYWCZE");
        shopHeaderList.add("TESCO /POLSKA/ SP Z.O.O");
        shopHeaderList.add("SKLEP ZABKA");
    }

    protected static Receipt parseString(String text){
        System.out.println(text);

        int minEditLength = 100;
        Receipt receipt = null;

        Scanner scaner = new Scanner(text);
        String line = scaner.nextLine();
        int tmp = 0;
        int LD;
        for (int i = 0; i < shopHeaderList.size(); i++) {
            LD = StringUtils.getLevenshteinDistance(line, shopHeaderList.get(i));
            if (LD < minEditLength) {
                minEditLength = LD;
                tmp = i;
            }
            //System.out.println(LD);
        }
        //System.out.println(tmp);
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

        receipt.setDate(text);
        receipt.setProductList(text);
        receipt.setSum(text);

        return receipt;
    }
}
