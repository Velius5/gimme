/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.controller.api;

import org.apache.http.client.methods.HttpGet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApiTestController {
    
    HttpGet ht = new HttpGet("http://localhost/getall");
    
    @RequestMapping("/apitest")
    public String apiPage() {
        return "home";
    }
}
