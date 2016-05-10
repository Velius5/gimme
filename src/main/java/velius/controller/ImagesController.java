/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import static java.util.Arrays.stream;
import java.util.Date;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import velius.model.Receipt;
import velius.model.User;
import velius.service.ReceiptService;
import velius.service.UserService;

@Controller
public class ImagesController {
    
    @Autowired
    UserService userService;
    
    @Autowired
    ReceiptService receiptService;

    @ResponseBody
    @RequestMapping(value = "/userphoto/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getUserPhoto(@PathVariable("id") Long id) throws IOException {
        User user = userService.getUser(id);
        byte[] img = null;
        if(user == null || user.getImage() == null) {
            ClassPathResource classPathResource = new ClassPathResource("static/images/icons/default-user.png");
            InputStream is = classPathResource.getInputStream();
            img = IOUtils.toByteArray(is);
            return img;
        } else {
            return user.getImage();
        }
    }
    
    @ResponseBody
    @RequestMapping(value = "/userphoto/{id}/{timestamp}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getUserPhotoWithoutCache(@PathVariable("id") Long id, @PathVariable("timestamp") String date) throws IOException {
        User user = userService.getUser(id);
        byte[] img = null;
        if(user == null || user.getImage() == null) {
            ClassPathResource classPathResource = new ClassPathResource("static/images/icons/default-user.png");
            InputStream is = classPathResource.getInputStream();
            img = IOUtils.toByteArray(is);
            return img;
        } else {
            return user.getImage();
        }
    }
    
    @ResponseBody
    @RequestMapping(value = "/receiptphoto/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getReceiptPhoto(@PathVariable("id") Long id) throws IOException {
        Receipt receipt = receiptService.findById(id);
        byte[] img = null;
        if(receipt == null || receipt.getImage() == null) {
            ClassPathResource classPathResource = new ClassPathResource("static/images/icons/receipt_icon.png");
            InputStream is = classPathResource.getInputStream();
            img = IOUtils.toByteArray(is);
            return img;
        } else {
            return receipt.getImage();
        }
    }
    
}
