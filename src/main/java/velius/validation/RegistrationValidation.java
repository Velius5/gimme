/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import velius.dto.RegistrationDTO;
import velius.service.UserService;

@Component("registrationValidator")
public class RegistrationValidation {
    @Autowired
    UserService userService;
    
    public boolean supports(Class<?> klass) {
      return RegistrationDTO.class.isAssignableFrom(klass);
    }

    public void validate(Object target, Errors errors) {
      RegistrationDTO registrationDTO = (RegistrationDTO) target;

      if (!(registrationDTO.getPassword()).equals(registrationDTO
          .getPasswordVerification())) {
        errors.rejectValue("passwordVerification",
            "matchingPassword.registrationDTO.passwordVerification",
            "Powtórz poprawnie hasło.");
      }
      
      if (!(userService.getUserByEmail(registrationDTO.getEmail()) == null)) {
      errors.rejectValue("email",
          "userExists.registrationDTO.email",
          "Podany adres email jest już wykorzystywany.");
    }
      
    }
}