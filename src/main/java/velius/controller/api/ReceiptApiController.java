/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.controller.api;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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
    public Receipt addReceipt(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id) throws IOException {
        List<Product> productList = new ArrayList<Product>();
        User owner = userService.getUser(id);
        byte[] image = file.getBytes();
        Receipt receipt = new Receipt(image);
        
        InputStream in = new ByteArrayInputStream(image);
	BufferedImage img = ImageIO.read(in);
        
        pl.piotr.Receipt tempReceipt = TessOCR.recognizeReceipt(img);
        receipt.setDate(tempReceipt.getDate());
        System.out.println(tempReceipt.getDate());
        receipt.setName(tempReceipt.getShopName());
        receipt.setOwner(owner);
        receipt.setSum(BigDecimal.valueOf(tempReceipt.getSum()));
        receiptService.save(receipt);
        
        for(pl.piotr.Product product : tempReceipt.getProductList()) {
            Product prod = new Product(product.getName(), BigDecimal.valueOf(product.getPrice()), (double)product.getCount(), 
            owner,receipt);
            productList.add(prod);
        }
        
        receipt.setProductList(productList);
        receiptService.save(receipt);
        
        System.out.println("Dodano paragon."); 
        return receipt;
    }
    
    /*@RequestMapping(value = "/test")
    public Receipt testReceipts() throws IOException {
        File img = new File("C:\\paragon.jpg");
        pl.piotr.Receipt tempReceipt = TessOCR.recognizeReceipt(img);
        
        return receipt;
    }*/
    
}
