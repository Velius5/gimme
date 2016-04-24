package pl.piotr.ReceiptsTemplates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *Klasa implementująca klasę abstrakcyjną Receipt. 
 * Dostarcza szablon paragonu dla sklepów sieci Żabka.
 **/
public class Zabka extends Receipt {

   
   @Override
    public void setDate(String txt) {
        Scanner scaner = new Scanner(txt);
        boolean foundDate = false;
        String date = null;

        while (scaner.hasNextLine() && !foundDate) {
            date = scaner.findInLine(Pattern.compile("\\w{4}(-|~)\\w{2}(-|~)\\w{2}"));
            if (date != null) {
                System.out.println(date);
                foundDate = true;
            }
            scaner.nextLine();
        }// throw exception
        if (date != null) {
            char[] tab = date.toCharArray();
            for (int i = 0; i < date.length(); i++) {
                switch (tab[i]) {
                    case 'O':
                        tab[i] = '0';
                    case 'I':
                        tab[i] = '1';
                        break;
                    case 'L':
                        tab[i] = '1';
                        break;
                    case 'J':
                        tab[i] = '1';
                        break;
                    case 'Z':
                        tab[i] = '2';
                        break;
                    case 'S':
                        tab[i] = '5';
                        break;
                    case '~':
                        tab[i] = '-';
                        break;
                }
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                this.date = format.parse(new String(tab));
            } catch (ParseException ex) {
                Logger.getLogger(Biedronka.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println(this.date);
        }
    }

    @Override
    public void setProductList(String txt) {
        this.productList = new ArrayList<>();
        Scanner scaner = new Scanner(txt);
        String temp = "PARAGON FISKALNY";
        while (scaner.hasNextLine()) {
            String line = scaner.nextLine();
            int j = 0;
            int i;
            for (i = 0; i < line.length() && i < temp.length(); i++)
                if (line.charAt(i) == temp.charAt(i)) j++;
            if ((float) j / i > 0.6) break;  
        }
        temp = "SPRZEDAZ OPODATK";
        while (scaner.hasNextLine()) {
            String name = scaner.findInLine(Pattern.compile("(\\p{Alnum}|\\p{Space}|[ĄĆĘŁŃÓŹŻ]){1,18}"));
            int j = 0;
            int i;
            for (i = 0; i < temp.length() && i < name.length(); i++)
                if (temp.charAt(i) == name.charAt(i)) j++;
            if ((float) j / i > 0.75) break; 
            
            String count = scaner.findInLine("(\\d){1,2}(\\.|\\,)(\\d){3}");
            if (count != null) {
                char[] tab = count.toCharArray();
                for (i = 0; i < count.length(); i++) {
                    switch (tab[i]) {
                        case 'O':
                            tab[i] = '0';
                        case 'I':
                            tab[i] = '1';
                            break;
                        case 'L':
                            tab[i] = '1';
                            break;
                        case 'J':
                            tab[i] = '1';
                            break;
                        case 'Z':
                            tab[i] = '2';
                            break;
                        case 'S':
                            tab[i] = '5';
                            break;
                        case ',':
                            tab[i] = '.';
                            break;
                    }
                }
                count = new String(tab);
            }
            String price = scaner.findInLine("(\\d){1,}(\\.|\\,)(\\d){2}");
            if (price != null) {
                char[] tab = price.toCharArray();
                for (i = 0; i < price.length(); i++) {
                    switch (tab[i]) {
                        case 'O':
                            tab[i] = '0';
                        case 'I':
                            tab[i] = '1';
                            break;
                        case 'L':
                            tab[i] = '1';
                            break;
                        case 'J':
                            tab[i] = '1';
                            break;
                        case 'Z':
                            tab[i] = '2';
                            break;
                        case 'S':
                            tab[i] = '5';
                            break;
                        case ',':
                            tab[i] = '.';
                            break;
                    }
                }
                price = new String(tab);
            }
            try{
                productList.add(new Product(name,Float.parseFloat(price),Float.parseFloat(count)));
            }catch(NullPointerException ex){
                if(name != null){
                    Product prod = new Product();
                    prod.setName(name);
                    if(price == null) prod.setPrice(0);
                    if(count == null) prod.setCount(1);
                    productList.add(prod);
                    System.out.println(name + "    " + prod.getPrice() + "   " + prod.getCount());
                }
            }
            
            scaner.nextLine();
        }
    }

    @Override
    public void setSum(String txt) {
        Scanner scaner = new Scanner(txt);
        while(scaner.hasNextLine()){
            String temp = scaner.findInLine("(?>SUM.\\s{1,}PLN)\\s");
            if(temp != null){
                
                String sum = scaner.findInLine("(\\w){1,3}(,|.)?(\\w){0,2}");
                System.out.println(sum);
                char[] tab = sum.toCharArray();
                for (int i = 0; i < sum.length(); i++) {
                    switch (tab[i]) {
                        case 'O':
                            tab[i] = '0';
                        case 'I':
                            tab[i] = '1';
                            break;
                        case 'L':
                            tab[i] = '1';
                            break;
                        case 'J':
                            tab[i] = '1';
                            break;
                        case 'Z':
                            tab[i] = '2';
                            break;
                        case 'S':
                            tab[i] = '5';
                            break;
                        case ',':
                            tab[i] = '.';
                            break;
                    }
                }
                this.sum = Float.parseFloat(new String(tab));
                System.out.println(this.sum);
            }
            scaner.nextLine();
        }
    }
    
    
}
