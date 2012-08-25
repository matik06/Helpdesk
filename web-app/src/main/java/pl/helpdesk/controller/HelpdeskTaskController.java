/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import pl.helpdesk.model.Task;
import pl.helpdesk.service.GenericService;
import pl.helpdesk.service.TaskService;

/**
 *
 * @author matik06
 */
@Controller
@Scope(value = "view")
public class HelpdeskTaskController extends GridController<Task> implements Serializable {
    
    @Autowired
    TaskService taskService;
    
    public HelpdeskTaskController() {
        super(Task.class);
    }

    @Override
    public GenericService<Task, Integer> getService() {
        return taskService;
    }        
}
