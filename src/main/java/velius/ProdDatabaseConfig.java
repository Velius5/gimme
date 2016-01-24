/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import velius.model.User;
import velius.repository.UserRepository;

@Configuration
@Profile("prod")
public class ProdDatabaseConfig {
    
    @Autowired
    UserRepository userRepository;

    public ProdDatabaseConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ProdDatabaseConfig() {
    }
    
    @PostConstruct
    public void populateDatabase() {
        userRepository.save(new User("Patryk", "Dobrzyński", "kontakt@patrykdobrzynski.eu", "e10adc3949ba59abbe56e057f20f883e", true, 1));
        userRepository.save(new User("Andrzej", "Kowalski", "andrzej@wp.pl", "e10adc3949ba59abbe56e057f20f883e", true, 2));
        userRepository.save(new User("Robert", "Nowak", "robert555@gmail.com", "e10adc3949ba59abbe56e057f20f883e", true, 2));
        userRepository.save(new User("Mateusz", "Jakiśtam", "mati22@wp.pl", "e10adc3949ba59abbe56e057f20f883e", false, 2));
        userRepository.save(new User("Eugeniusz", "Jakieśnazwisko", "eugeniusz@wp.pl", "e10adc3949ba59abbe56e057f20f883e", true, 2));
    }
    
}
