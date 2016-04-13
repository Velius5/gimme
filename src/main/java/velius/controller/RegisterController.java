/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import velius.dto.RegistrationDTO;
import velius.model.User;

import velius.service.UserService;
import velius.utils.HashGeneratorUtils;
import velius.validation.RegistrationValidation;

@Controller
public class RegisterController {
    @Autowired
    UserService userService;
    @Autowired
    RegistrationValidation registrationValidation;
    
    @RequestMapping(value="/register", method=RequestMethod.GET)
    public String showRegistration(Model model) {
        RegistrationDTO registrationDTO = new RegistrationDTO();
	model.addAttribute("registration", registrationDTO);
        return "register";
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String processRegistration(@ModelAttribute("registration") @Valid RegistrationDTO registrationDTO, BindingResult result) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        registrationValidation.validate(registrationDTO, result);
        if (result.hasErrors()) {
            //formularz nie jest uzupełniony prawidłowo
            return "register";
        } else {
            //formularz wypełniony prawidłowo
            String password = HashGeneratorUtils.generateMD5(registrationDTO.getPassword());
            String name = registrationDTO.getName();
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            String surname = registrationDTO.getSurname();
            surname = surname.substring(0, 1).toUpperCase() + surname.substring(1);
            userService.save(new User(name, surname, registrationDTO.getEmail(), password, false, 2));
            return "register_success";
        }
    }
}
