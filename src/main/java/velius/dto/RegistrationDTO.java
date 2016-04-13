/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.dto;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


public class RegistrationDTO {

    @NotEmpty(message = "Imię nie może być puste")
    @Size(max = 100)
    private String name;
    
    @NotEmpty(message = "Nazwisko nie może być puste")
    @Size(max = 100)
    private String surname;
    
    @Email(message = "Podaj poprawny adres email")
    @NotEmpty(message = "Email nie może być pusty")
    @Size(max = 100)
    private String email;

    @NotEmpty(message = "Hasło nie może być puste")
    @Size(min = 6, max = 50, message = "Hasło musi zawierać co najmniej 6 znaków")
    private String password;
    
    @NotEmpty(message = "Musisz potwierdzić hasło")
    @Size(min = 6, max = 50, message = "")
    private String passwordVerification;

    public RegistrationDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordVerification() {
        return passwordVerification;
    }

    public void setPasswordVerification(String passwordVerification) {
        this.passwordVerification = passwordVerification;
    }
    
    
    
    

}
