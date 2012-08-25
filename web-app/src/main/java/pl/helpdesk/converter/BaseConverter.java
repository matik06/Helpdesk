/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import pl.helpdesk.model.BaseEntity;
import pl.helpdesk.service.GenericService;

/**
 *
 * @author matik06
 */
public class BaseConverter<T extends BaseEntity<Integer>> implements Converter {
    
    GenericService<T, Integer> service;
    
    public BaseConverter(GenericService<T, Integer> service) {
        this.service = service;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        if (value == null || value.isEmpty()) {
            return null;
        }

        Integer id = Integer.parseInt(value);
        T entity = service.findById(id);

        return entity;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }

        T entity = (T) value;
        String val = Integer.toString(entity.getId());

        return val;
    }
    
}
