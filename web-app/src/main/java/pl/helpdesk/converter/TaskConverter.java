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
import pl.helpdesk.model.Task;
import pl.helpdesk.service.TaskService;

/**
 *
 * @author matik06
 */
@Component
public class TaskConverter implements Converter {      
    
    @Autowired
    TaskService taskService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value == null || value.isEmpty()) {
            return null;
        }
        
        Integer taskId = Integer.parseInt(value);
        Task task = taskService.findById(taskId);
        
        return task;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        
        if (value == null) {
            return null;
        }
        
        Task task = (Task) value;
        String val = Integer.toString(task.getId());
        
        return val;
    }
    
}
