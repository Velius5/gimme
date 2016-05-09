/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.controller.api;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.piotr.AbbyOCR;
import velius.model.Notification;
import velius.model.Product;
import velius.model.Receipt;
import velius.model.Response;
import velius.model.User;
import velius.service.NotificationService;
import velius.service.ProductService;
import velius.service.ReceiptService;
import velius.service.UserService;

@RestController
@RequestMapping(value = "/api/receipt")
public class ReceiptApiController {
    private final ReceiptService receiptService;
    private final UserService userService;
    private final ProductService productService;
    
    @Autowired
    NotificationService notificationService;

    @Autowired
    public ReceiptApiController(ReceiptService receiptService, UserService userService, ProductService productService) {
        this.receiptService = receiptService;
        this.userService = userService;
        this.productService = productService;
    }
    
    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    public Receipt addReceipt(@RequestParam String file, HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id) throws IOException {
        List<Product> productList = new ArrayList<>();
        User owner = userService.getUser(id);      
        //byte[] image=file.getBytes();
        byte[] image = Base64.decodeBase64(file);
        System.out.println(image.length/1024 + "kb");
        //byte[] image = Base64.decodeBase64(byteArr);
        
        pl.piotr.ReceiptsTemplates.Receipt tempReceipt=null;
        try {
            tempReceipt = AbbyOCR.recognizeReceipt(image);
        } catch (Exception ex) {
            Logger.getLogger(ReceiptApiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Receipt receipt = new Receipt(tempReceipt,image,owner);
        receiptService.save(receipt);
        
        System.out.println("Dodano paragon."); 
       
        return receipt;
        /*Receipt rec = receiptService.findById(new Long("7"));
        return rec;       */
    }
    
    @RequestMapping(value = "/edit/{id}",method = RequestMethod.POST)
    public Response editProducts(@PathVariable("id") Long id, @RequestParam List<String> productsUsersList){
        Receipt rec = receiptService.findById(id);
        System.out.println("Pobieranie paragonu");
        if(rec != null) {
            int i = 0;
            for(Product p : rec.getProductList()) {
                List<User> usersList = new ArrayList<>();
                String s = productsUsersList.get(i);
                System.out.println(s);
                if(s.contains(",")) {
                    String[] parts = s.split(",");
                    int j = 1;
                    p.setProductName(parts[j]);
                    j++;
                    p.setCount(Double.valueOf(parts[j]));
                    j++;
                    p.setPrice(BigDecimal.valueOf(Double.valueOf(parts[j])));
                    j++;
                    while(j < parts.length) {
                        Long userId = Long.valueOf(parts[j]);
                        User user = userService.getUser(userId);
                        p.getUsers().add(user);
                        usersList.add(user);
                        j++;
                    }
                }
                //productService.save(p);
                for(User usr : usersList) {
                    System.out.println(p.getId() + " " + usr.getName());
                    notificationService.save(new Notification("receipt", rec.getDate(), "Użytkownik " + rec.getOwner().getName() + " " + rec.getOwner().getSurname() + " oznaczył Cię w swoim paragonie. Musisz mu oddać za " + p.getProductName() + ".", false, usr.getId()));
                }
                i++;
            }
            rec.setProductPricePerPerson();
            receiptService.save(rec);
            notificationService.save(new Notification("receipt", rec.getDate(), "Dodano paragon ze sklepu " + rec.getName(), true, rec.getOwner().getId()));
            return new Response(true);
        } else {
            return new Response(false);
        }

    }
    
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public Receipt showReceipt(@PathVariable("id") Long id){
        Receipt receipt = receiptService.findById(id);
        return receipt;
    }
    
    @RequestMapping(value = "/getall/{id}", method = RequestMethod.GET)
    public List<Long> getAllReceipts(@PathVariable("id") Long id){
        User user = userService.getUser(id);
        List<Receipt> recList = receiptService.findAllByOwner(user);
        
        List<Long> idList = new ArrayList<>();
        for(Receipt rec : recList)
            idList.add(rec.getId());
        return idList;
    }
    
    
    /*@RequestMapping(value = "/test")
    public Receipt testReceipts() throws IOException {
        File img = new File("C:\\paragon.jpg");
        pl.piotr.Receipt tempReceipt = TessOCR.recognizeReceipt(img);
        
        return receipt;
    }*/
    
}
