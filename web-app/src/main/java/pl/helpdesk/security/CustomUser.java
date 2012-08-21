/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author matik06
 */
public class CustomUser extends User{
    
    private static boolean enabled = true;
    private static boolean accountNonExpired = true;
    private static boolean credentialsNonExpired = true;
    private static boolean accountNonLocked = true;
    
    private pl.helpdesk.model.User user;

    
    public CustomUser(pl.helpdesk.model.User user) {                
        super(user.getLogin(),
                user.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                new ArrayList<GrantedAuthority>());
        
        this.user = user;        
    }
    
    
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);
        
        return authorities;
    }

    public pl.helpdesk.model.User getUser() {
        return user;
    }        
}
