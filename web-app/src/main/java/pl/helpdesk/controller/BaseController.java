/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller;

import java.io.Serializable;
import javax.faces.context.FacesContext;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.helpdesk.model.CustomerUser;
import pl.helpdesk.model.HelpdeskUser;
import pl.helpdesk.model.User;
import pl.helpdesk.security.CustomUser;

/**
 *
 * @author matik06
 */
public abstract class BaseController implements Serializable {
    
    protected String getRequestParameterAsString(String key) {
        String result = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
        System.out.println("Trying read request parameter " + key + ":" + result);
        return result;
    }
    
    protected Integer getRequestParameterAsInt(String key) {
        String stringValue = getRequestParameterAsString(key);
        return Integer.valueOf(stringValue);
    }
    
    public User getLoggedUser() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();        
        
        return customUser.getUser();
    }
    
    protected HelpdeskUser getLoggedHelpdeskUser() {
        User user = getLoggedUser();
        return (HelpdeskUser)user;
    }
    
    protected CustomerUser getLoggedCustomerUser() {
        User user = getLoggedUser();
        return (CustomerUser)user;
    }
}
