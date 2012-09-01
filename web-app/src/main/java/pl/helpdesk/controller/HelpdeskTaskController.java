/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller;

import java.util.Date;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import pl.helpdesk.constant.StatusEnum;
import pl.helpdesk.model.Status;
import pl.helpdesk.model.Task;
import pl.helpdesk.model.User;
import pl.helpdesk.service.GenericService;
import pl.helpdesk.service.StatusService;
import pl.helpdesk.service.TaskService;

/**
 *
 * @author matik06
 */
public abstract class HelpdeskTaskController extends GridController<Task> {
    
    @Autowired
    TaskService taskService;
    @Autowired
    protected StatusService statusService;
    
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
            Status taskStatus = statusService.findById(StatusEnum.IN_PROGRESS.getValue());
            entity.setStatus(taskStatus);

            getService().save(entity);
        } else {
            getService().update(entity);
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
