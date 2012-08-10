/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import pl.helpdesk.model.Role;
import pl.helpdesk.service.RoleService;

/**
 *
 * @author matik06
 */
@Controller
@Scope(value="globalSession")
public class ConstantController implements Serializable {
    
    @Autowired
    RoleService roleService;
    
    private List<SelectItem> helpdeskRoles = new ArrayList<>();
    private List<SelectItem> customerRoles = new ArrayList<>();
    private List<SelectItem> allRoles = new ArrayList<>();
    
    @PostConstruct
    public void init() {
        allRoles.add(getEmpty());
        List<Role> roles = roleService.findAll();
        for (Role role : roles) {
            SelectItem item = new SelectItem(role, role.getName());
            allRoles.add(item);
        }        
    }
    
    private SelectItem getEmpty() {
        return new SelectItem(null, "");
    }
    
    //gettery i settery

    public List<SelectItem> getAllRoles() {
        return allRoles;
    }

    public void setAllRoles(List<SelectItem> allRoles) {
        this.allRoles = allRoles;
    }

    public List<SelectItem> getCustomerRoles() {
        return customerRoles;
    }

    public void setCustomerRoles(List<SelectItem> customerRoles) {
        this.customerRoles = customerRoles;
    }

    public List<SelectItem> getHelpdeskRoles() {
        return helpdeskRoles;
    }

    public void setHelpdeskRoles(List<SelectItem> helpdeskRoles) {
        this.helpdeskRoles = helpdeskRoles;
    }        
}
