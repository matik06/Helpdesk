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
import pl.helpdesk.model.HelpdeskUser;
import pl.helpdesk.service.HelpdeskUserService;

/**
 *
 * @author matik06
 */
@Component
public class HelpdeskUserConverter implements Converter {
    
    @Autowired
    HelpdeskUserService service;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        Integer id = Integer.parseInt(value);
        HelpdeskUser helpdeskUser = service.findById(id);

        return helpdeskUser;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }

        HelpdeskUser helpdeskUser = (HelpdeskUser) value;
        String val = Integer.toString(helpdeskUser.getId());

        return val;
    }
    
}
