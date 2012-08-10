/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.helpdesk.model.Role;
import pl.helpdesk.service.RoleService;

/**
 *
 * @author matik06
 */
@Component
public class RoleConverter implements Converter {      
    
    @Autowired
    RoleService roleService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value == null || value.isEmpty()) {
            return null;
        }
        
        Integer roleId = Integer.parseInt(value);
        Role role = roleService.findById(roleId);
        
        return role;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        if (value == null) {
            return null;
        }
        
        Role role = (Role) value;
        String val = Integer.toString(role.getId());
        
        return val;
    }
    
}
