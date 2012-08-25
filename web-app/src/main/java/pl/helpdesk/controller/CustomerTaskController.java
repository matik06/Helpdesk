/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import pl.helpdesk.constant.StatusEnum;
import pl.helpdesk.model.Customer;
import pl.helpdesk.model.Status;
import pl.helpdesk.model.Task;
import pl.helpdesk.service.GenericService;
import pl.helpdesk.service.StatusService;
import pl.helpdesk.service.TaskService;

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
        } else {
            getService().update(entity);
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
    
    public void goToTaskDetails() {                
        redirect("/views/task-detail.xhtml?customer=9?faces-redirect=true");
    }
    
}
