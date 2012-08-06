/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
@Scope(value="session")
public class ConstantController implements Serializable {
    
    @Autowired
    RoleService roleService;
    
    public List<SelectItem> getAllRoles() {
        List<SelectItem> result = new ArrayList<>();
        
        List<Role> roles = roleService.findAll();
        for (Role role : roles) {
            SelectItem item = new SelectItem(role, role.getName());
            result.add(item);
        }
        
        return result;
    }
}
