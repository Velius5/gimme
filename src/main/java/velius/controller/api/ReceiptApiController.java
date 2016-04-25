/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.controller.api;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.piotr.AbbyOCR;
import pl.piotr.TessOCR;
import velius.model.Product;
import velius.model.Receipt;
import velius.model.User;
import velius.service.ReceiptService;
import velius.service.UserService;

@RestController
@RequestMapping(value = "/api/receipt")
public class ReceiptApiController {
    private final ReceiptService receiptService;
    private final UserService userService;

    @Autowired
    public ReceiptApiController(ReceiptService receiptService, UserService userService) {
        this.receiptService = receiptService;
        this.userService = userService;
    }
    
    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    public Receipt addReceipt(@RequestParam String file, HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id) throws IOException {
        List<Product> productList = new ArrayList<Product>();
        User owner = userService.getUser(id);      
        //byte[] image=file.getBytes();
        byte[] image = Base64.decodeBase64(file);
        System.out.println(image.length/1024 + "kb");
        //byte[] image = Base64.decodeBase64(byteArr);
        String img = Base64.encodeBase64String(image);
        
        pl.piotr.ReceiptsTemplates.Receipt tempReceipt=null;
        try {
            tempReceipt = TessOCR.recognizeReceipt(image);
        } catch (Exception ex) {
            Logger.getLogger(ReceiptApiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Receipt receipt = new Receipt(tempReceipt,image,owner);
        receiptService.save(receipt);
        
        System.out.println("Dodano paragon."); 
        return receipt;
    }
    
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public Receipt showReceipt(@PathVariable("id") Long id){
        Receipt receipt = receiptService.findById(id);
        return receipt;
    }
    
    
    /*@RequestMapping(value = "/test")
    public Receipt testReceipts() throws IOException {
        File img = new File("C:\\paragon.jpg");
        pl.piotr.Receipt tempReceipt = TessOCR.recognizeReceipt(img);
        
        return receipt;
    }*/
    
}
