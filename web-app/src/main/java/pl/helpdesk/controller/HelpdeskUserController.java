/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
    }
    
    @Override
    public GenericService<HelpdeskUser, Integer> getService() {
        return helpdeskUserService;
    }        
}
