/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller;

import java.util.Date;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import pl.helpdesk.constant.EventTypeEnum;
import pl.helpdesk.constant.StatusEnum;
import pl.helpdesk.model.Status;
import pl.helpdesk.model.Task;
import pl.helpdesk.model.User;
import pl.helpdesk.service.GenericService;
import pl.helpdesk.service.StatusService;
import pl.helpdesk.service.TaskService;
import pl.helpdesk.service.custom.TaskNotificationService;

/**
 *
 * @author matik06
 */
public abstract class HelpdeskTaskController extends GridController<Task> {
    
    @Autowired
    protected TaskService taskService;
    @Autowired
    protected StatusService statusService;
    @Autowired
    protected TaskNotificationService notificationService; 
    
    public HelpdeskTaskController() {
        super(Task.class);
    }
    
    @Override
    protected abstract void reloadList();

    
    @Override
    @PostConstruct
    public void init() {
        reloadList();
    }
    
    @Override
    public void saveOrUpdateEntity() {

        if (entity.getId() == null) {
            entity.setAuthor(getLoggedUser());
            entity.setDate(new Date());
            Status taskStatus = statusService.findById(StatusEnum.NOT_STARTED.getValue());
            entity.setStatus(taskStatus);

            getService().save(entity);
            notificationService.addTaskNotification(entity, EventTypeEnum.CREATED_TASK, getLoggedUser());
        } else {
            getService().update(entity);
            notificationService.addTaskNotification(entity, EventTypeEnum.EDIT_TASK, getLoggedUser());
        }

        reloadList();
    }
    
    public void updateEntity() {

        getService().update(entity);
        reloadList();
    }
    
    @Override
    public GenericService<Task, Integer> getService() {
        return taskService;
    }    
    
    public boolean getCanEdit() {
        User user = getLoggedUser();
        if (entity == null) {
            return true;
        } else {
            return entity.canEdit(user);
        }        
    }
}
