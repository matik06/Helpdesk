/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.util;

import org.springframework.security.core.context.SecurityContextHolder;
import pl.helpdesk.model.User;
import pl.helpdesk.security.CustomUser;

/**
 *
 * @author matik06
 */
public class SpringSecurityUtil {
    
    public static User getLoggedUser() {
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUser.getUser();
    }
}
