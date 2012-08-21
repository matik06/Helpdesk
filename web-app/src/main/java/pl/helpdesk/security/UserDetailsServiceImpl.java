/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.helpdesk.service.CustomerUserService;
import pl.helpdesk.service.HelpdeskUserService;

/**
 *
 * @author matik06
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

//    private HashMap<String, User> users = new HashMap<>();
    
    private static final Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);
    
    @Autowired
    HelpdeskUserService helpdeskUserService;
    CustomerUserService customerUserService;
    
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        
        try {
            pl.helpdesk.model.User user;

            user = helpdeskUserService.getByLogin(login);
            if (user == null) {
                user = customerUserService.getByLogin(login);
            }

            if (user == null) {
                throw new UsernameNotFoundException("UserAccount for name \"" + login + "\" not found.");
            }

            return new CustomUser(user);
        } catch (Exception e) {
            logger.error(e);
            throw new UsernameNotFoundException("Some problem with database connection.");
        }        
    }
}
