/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import velius.dto.EditProfileDTO;
import velius.service.UserService;

@Component("editProfileValidator")
public class EditProfileValidation {
    @Autowired
    UserService userService;
    
    public boolean supports(Class<?> klass) {
      return EditProfileDTO.class.isAssignableFrom(klass);
    }

    public void validate(Object target, Errors errors) {
      EditProfileDTO editProfileDTO = (EditProfileDTO) target;

      if (!(editProfileDTO.getPassword()).equals(editProfileDTO
          .getPasswordVerification())) {
        errors.rejectValue("passwordVerification",
            "matchingPassword.editProfileDTO.passwordVerification",
            "Powtórz poprawnie hasło.");
      }
      
    }
}