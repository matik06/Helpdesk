/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author matik06
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private HashMap<String, User> users = new HashMap<>();
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.get(username);
        
        if (user == null) {
            throw new UsernameNotFoundException("UserAccount for name \""+ username + "\" not found.");
        }
        
        return user;
    }
    
    @PostConstruct
    public void init() {
        Collection<GrantedAuthority> adminAuthorities = new ArrayList<>();
        adminAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        
        Collection<GrantedAuthority> userAuthorities = new ArrayList<>();
        userAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        
        users.put("admin", new User("admin", "admin", enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, adminAuthorities));
        users.put("user", new User("user", "user", enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, userAuthorities));
    }
}
