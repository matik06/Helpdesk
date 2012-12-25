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
import pl.helpdesk.model.CustomerUser;
import pl.helpdesk.model.HelpdeskUser;
import pl.helpdesk.service.CustomerUserService;

/**
 *
 * @author MAT1K
 */
@Component
public class CustomerUserConverter implements Converter {
    
    @Autowired
    CustomerUserService service;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        Integer id = Integer.parseInt(value);
        CustomerUser customerUser = service.findById(id);

        return customerUser;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }

        CustomerUser customerUser = (CustomerUser) value;
        String val = Integer.toString(customerUser.getId());

        return val;
    }
    
}

