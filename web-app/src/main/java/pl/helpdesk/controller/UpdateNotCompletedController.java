/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller;

import java.util.Date;
import java.util.List;
import javax.faces.model.SelectItem;
import org.primefaces.event.FlowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import pl.helpdesk.constant.EventTypeEnum;
import pl.helpdesk.constant.StatusEnum;
import pl.helpdesk.model.Customer;
import pl.helpdesk.model.Status;
import pl.helpdesk.model.Task;
import pl.helpdesk.model.Upgrade;
import pl.helpdesk.service.GenericService;
import pl.helpdesk.service.StatusService;
import pl.helpdesk.service.TaskService;
import pl.helpdesk.service.UpgradeService;
import pl.helpdesk.service.impl.TaskNotificationService;

/**
 *
 * @author MAT1K
 */
@Controller
@Scope(value = "view")
public class UpdateNotCompletedController extends GridController<Upgrade> {
    
    @Autowired
    protected UpgradeService upgradeService;
    @Autowired
    ConstantController constantControler;
    @Autowired
    TaskService taskService;
    @Autowired
    StatusService statusService;
    @Autowired
    TaskNotificationService notificationService;
    
    private List<Task> tasks;
    
    public UpdateNotCompletedController() {
        super(Upgrade.class);
    }

    @Override
    public GenericService getService() {
        return upgradeService;
    }
    
    @Override
    public void saveOrUpdateEntity() {        

        if (entity.getId() == null) {
            entity.setIsCompleted(false);
            entity.setDate(new Date());
            entity.setUser(getLoggedUser());                               
            
            getService().save(entity);   
            
            Status status = statusService.findById(StatusEnum.CLOSED.getValue());
            for (Task task : tasks) {                
                task.setUpgrade(entity);                    
                taskService.update(task);                                    
            }            
        } else {
            getService().update(entity);
        }

        reloadList();
        clear();
    }

    @Override
    protected void reloadList() {
        entityList = upgradeService.findAll(false);
    }
    
    
    
    public List<SelectItem>getAllCustomerUsers() {        
        return constantControler.prepareList(tasks, "title");
    }
    
    public List<SelectItem> getTaksForUpdate() {
        
        Customer customer = this.entity.getCustomer();
        tasks = taskService.findByCustomer(customer, StatusEnum.READY_FOR_UPGRADE, StatusEnum.CLOSED);
        
        return constantControler.prepareList(tasks, "title", false);
    }
    
    public String onFlowProcess(FlowEvent event) {
        return event.getNewStep();        
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }        
    
    
}
