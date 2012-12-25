/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.helpdesk.model.Priority;
import pl.helpdesk.service.PriorityService;

/**
 *
 * @author MAT1K
 */
@Component
public class PriorityConverter extends BaseConverter<Priority>{
    @Autowired
    public PriorityConverter(PriorityService service) {
        super(service);
    }
}
