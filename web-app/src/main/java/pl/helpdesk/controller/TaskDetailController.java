/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import pl.helpdesk.model.Task;
import pl.helpdesk.service.TaskService;

/**
 *
 * @author matik06
 */
@Controller
@Scope(value = "view")
public class TaskDetailController extends BaseController {
    
    @Autowired
    private TaskService taskService;
    private Task task;
    
    @PostConstruct
    public void init() throws InstantiationException, IllegalAccessException {
        int taskId = getRequestParameterAsInt("taskId");
        System.out.println("taskId: " + taskId);
        task = taskService.findById(taskId);
        System.out.println("task");
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }        
}
