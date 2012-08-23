/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import pl.helpdesk.model.HelpdeskUser;
import pl.helpdesk.service.GenericService;
import pl.helpdesk.service.HelpdeskUserService;


/**
 *
 * @author matik06
 */
@Controller
@Scope(value="view")
public class HelpdeskUserController extends GridController<HelpdeskUser> implements Serializable {
    
    @Autowired    
    HelpdeskUserService helpdeskUserService;   
    
    
    public HelpdeskUserController() {                
        
        super(HelpdeskUser.class);      
        
        String password = "plaintextPassword";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        System.out.println("hashed password");
        System.out.println(hashedPassword);
    }
    
    @Override
    public GenericService<HelpdeskUser, Integer> getService() {
        return helpdeskUserService;
    }        
}
