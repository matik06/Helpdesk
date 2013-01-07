/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import pl.helpdesk.model.CustomerUser;
import pl.helpdesk.model.HelpdeskUser;
import pl.helpdesk.model.User;
import pl.helpdesk.service.CustomerUserService;
import pl.helpdesk.service.HelpdeskUserService;
import pl.helpdesk.util.FacesUtils;

/**
 *
 * @author mlubanski
 */
@Controller
@Scope(value = "view")
public class SettingController extends BaseController {
    
    private static final Logger logger = Logger.getLogger(SettingController.class);
    
    @Autowired
    HelpdeskUserService helpdeskUserService;
    @Autowired
    CustomerUserService customerUserService;
    
    private User user;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    BCryptPasswordEncoder encoder;    
   
    private String currentPassword;
    private String newPassword;
    private String repeatedNewPassword;    
    
    public void validatePassword(FacesContext context, UIComponent component, Object value) {
        
        String currentPassword = (String)value;
        
        if (!encoder.matches(currentPassword, user.getPassword())) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Podane hasło jest nieprawidłowe", null));
        }    
    }
    
    public void changePassword() {
        String hashedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(hashedPassword);                                
        updateUserSettings();
        
        FacesUtils.addSuccessMessage("Hasło zostało zmienione");
    }
    
    public void updateUserSettings() {
        if (user instanceof HelpdeskUser) {
            helpdeskUserService.update((HelpdeskUser)user);
        } else if (user instanceof CustomerUser){
            customerUserService.update((CustomerUser)user);
        } else {
            logger.error("nieznany typ uzytkownika: " + user.getClass().getName());
        }
        
        FacesUtils.addSuccessMessage("Ustawienia zostały zapisane");
    }
    
    @PostConstruct
    public void init() throws InstantiationException, IllegalAccessException {
        this.user = getLoggedUser();
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepeatedNewPassword() {
        return repeatedNewPassword;
    }

    public void setRepeatedNewPassword(String repeatedNewPassword) {
        this.repeatedNewPassword = repeatedNewPassword;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }        
}
