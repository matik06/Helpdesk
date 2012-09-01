/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller;

import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author matik06
 */
@Controller
@Scope(value = "view")
public class TaskHelpdeskClosedController extends HelpdeskTaskController implements Serializable {

    @Override
    protected void reloadList() {
        entityList = taskService.findClosed();
    }
    
}
