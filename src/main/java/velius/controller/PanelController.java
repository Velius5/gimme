/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PanelController {
    @RequestMapping("/panel")
    public String panelPage(Model model) {
        model.addAttribute("pageMessage", "test");
        return "panel";
    }
}
