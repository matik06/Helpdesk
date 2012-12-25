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
import pl.helpdesk.model.Customer;
import pl.helpdesk.model.Status;
import pl.helpdesk.model.Task;
import pl.helpdesk.service.GenericService;
import pl.helpdesk.service.StatusService;
import pl.helpdesk.service.TaskService;
import pl.helpdesk.service.impl.TaskNotificationService;

/**
 *
 * @author matik06
 */
public abstract class CustomerTaskController extends GridController<Task> {
    
    @Autowired
    protected TaskService service;
    @Autowired
    protected StatusService statusService;
    Customer customer;
    @Autowired
    TaskNotificationService notificationService; 
    
    
    public CustomerTaskController() {
        super(Task.class);
    }
    
    @Override
    protected abstract void reloadList();
    
    @Override
    public void saveOrUpdateEntity() {

        if (entity.getId() == null) {
            entity.setCustomer(customer);
            entity.setAuthor(getLoggedUser());
            entity.setDate(new Date());
            Status taskStatus = statusService.findById(StatusEnum.NOT_STARTED.getValue());
            entity.setStatus(taskStatus);

            getService().save(entity);
            notificationService.addTaskNotification(entity, EventTypeEnum.CREATED_TASK, getLoggedCustomerUser());
        } else {
            getService().update(entity);
            notificationService.addTaskNotification(entity, EventTypeEnum.EDIT_TASK, getLoggedCustomerUser());
        }

        reloadList();
    }

    @Override
    @PostConstruct
    public void init() {

        try {
            this.customer = getLoggedCustomerUser().getCustomer();
        } catch (Exception e) {
        }

        reloadList();
    }
    
    @Override
    public GenericService<Task, Integer> getService() {
        return service;
    }        
}
