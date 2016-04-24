/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.piotr;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.piotr.ReceiptsTemplates.Receipt;

/**
 *
 * @author Piotr Czarny
 */
public class ReceiptParserTest {
    
    public ReceiptParserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {     
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testParseString() {
        System.out.println("parseString");
        System.out.println(System.getProperty("user.dir"));
        File plik = new File("./paragon.txt");
        String text = "";
        try {
            Scanner scaner = new Scanner(plik);
            while(scaner.hasNextLine())
                text = text.concat(scaner.nextLine()+ "\n");
        } catch (FileNotFoundException ex) {
            System.out.println("Nie znaleziono pliku");
            fail("pliku nie ma");
        }
        
        Receipt expResult = null;
        ReceiptParser.init();
        Receipt result = ReceiptParser.parseString(text.toUpperCase());
        assertEquals(0, 0);
    }
    
}
