/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import javax.validation.Valid;
import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import velius.dto.EditProfileDTO;
import velius.model.User;
import velius.service.UserService;
import velius.utils.HashGeneratorUtils;
import velius.validation.EditProfileValidation;

/**
 *  Kontroler obsługujący żądanie wyświetlenia strony zawierającej ustawienia
 * konta użytkownika. Dostarcza do widoku dane dotyczące użytkownika oraz obługuję zmiane
 * tych dancyh przez użytkownika.
 */
@Controller
public class PanelSettingsController {
    @Autowired
    UserService userService;
    
    @Autowired
    EditProfileValidation editProfileValidation;
    
    @RequestMapping(value="/panel/settings", method=RequestMethod.GET)
    public String panelSettingsPage(Model model, Principal principal) {
        String userPhoto;
        EditProfileDTO editProfileDTO = new EditProfileDTO();
	model.addAttribute("editProfile", editProfileDTO);
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userService.getUserByEmail(email);
        model.addAttribute("userName", user.getName());
        model.addAttribute("userSurname", user.getSurname());
        model.addAttribute("userEmail", user.getEmail());
        model.addAttribute("userId", user.getId());
        
        return "panel_settings";
    }
        
    @RequestMapping(value="/panel/settings", method=RequestMethod.POST)
    public String processForm(@ModelAttribute("editProfile") @Valid EditProfileDTO editProfileDTO, BindingResult result, @RequestParam(value = "photo", required = false) MultipartFile photo) throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException {
        editProfileValidation.validate(editProfileDTO, result);
        if (result.hasErrors()) {
            //formularz nie jest uzupełniony prawidłowo
        } else {
            //formularz wypełniony prawidłowo
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = auth.getName();
            User user = userService.getUserByEmail(email);
        
            
            String name = editProfileDTO.getName();
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            user.setName(name);
            
            String surname = editProfileDTO.getSurname();
            surname = surname.substring(0, 1).toUpperCase() + surname.substring(1);
            user.setSurname(surname);
            
            if(email != editProfileDTO.getEmail() && userService.getUserByEmail(editProfileDTO.getEmail()) == null) {
                user.setEmail(editProfileDTO.getEmail());
                SecurityContextHolder.clearContext();
            }
            
            if(!editProfileDTO.getPassword().isEmpty()) {
                String password = HashGeneratorUtils.generateMD5(editProfileDTO.getPassword());
                user.setPassword(password);
                SecurityContextHolder.clearContext();
            }
            
            if (!photo.isEmpty()) {
                user.setImage(photo.getBytes());
            }
                        
            
            userService.save(user);
        }
        return "redirect:settings";
    }
        
    
    

    
    
}
