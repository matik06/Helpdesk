/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.helpdesk.model.Status;
import pl.helpdesk.service.StatusService;

/**
 *
 * @author matik06
 */
@Component
public class StatusConverter extends BaseConverter<Status> {
    
    @Autowired         
    public StatusConverter(StatusService service) {
        super(service);
    }       
}
