/*
 * Author: Patryk Dobrzyński
 * Author URL: http://patrykdobrzynski.eu
 * Author Email: kontakt@patrykdobrzynski.eu
 */
package velius.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import velius.model.User;
import velius.repository.UserRepository;

@Repository
public class AuthUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserRepository users;
    private org.springframework.security.core.userdetails.User userdetails;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        
        User user = users.findByEmail(email);
        
        userdetails = new org.springframework.security.core.userdetails.User (user.getEmail(),
               user.getPassword(),
                user.isActive(),
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getAuthorities(user.getRole())
                );
    
        return (UserDetails) userdetails;
    }
    
    public List<GrantedAuthority> getAuthorities(Integer role) {
    
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        if (role.intValue() == 1) {
          authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if (role.intValue() == 2) {
          authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return authList;
    }
  
    
}
