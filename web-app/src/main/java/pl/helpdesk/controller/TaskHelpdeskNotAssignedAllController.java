/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller;

import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import pl.helpdesk.model.HelpdeskUser;

/**
 *
 * @author matik06
 */
@Controller
@Scope(value = "view")
public class TaskHelpdeskNotAssignedAllController extends HelpdeskTaskController implements Serializable {
    
    @Override
    protected void reloadList() {
        this.entityList = taskService.findNotAssigned();
    }
    
    public void assignMyself() {
        HelpdeskUser user = getLoggedHelpdeskUser();
        this.entity.setResponsible(user);
        updateEntity();
    }
}
