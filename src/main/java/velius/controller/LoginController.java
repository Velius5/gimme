/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Kontroler obsługujący żądanie wyświetlenia strony logowania
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }
}
