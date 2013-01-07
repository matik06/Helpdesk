/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller;


import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import pl.helpdesk.constant.EventTypeEnum;
import pl.helpdesk.constant.NoteTypeEnum;
import pl.helpdesk.constant.StatusEnum;
import pl.helpdesk.model.Status;
import pl.helpdesk.model.Task;
import pl.helpdesk.model.TaskNote;
import pl.helpdesk.model.Upgrade;
import pl.helpdesk.service.*;
import pl.helpdesk.service.impl.TaskNotificationService;


/**
 *
 * @author matik06
 */
@Controller
@Scope(value = "view")
public class UpdateDetailController extends BaseController {
    
    private static final Logger logger = Logger.getLogger(UpdateDetailController.class);
    
    @Autowired
    UpgradeService upgradeService;
    @Autowired 
    TaskService taskService;
    @Autowired
    TaskNotificationService notificationService;
    @Autowired
    TaskNoteService taskNoteService;
    @Autowired
    StatusService statusService;
    
    private Upgrade entity;        
    
    @PostConstruct
    public void init() throws InstantiationException, IllegalAccessException {
        int upgradeId = getRequestParameterAsInt("upgradeId");
        entity = upgradeService.findById(upgradeId);                
    }
    
    public List<Task> getTasks() {
        return taskService.findByUpgrade(entity);
    }
    
//    private void reload() {        
//        super.
//        int upgradeId = entity.getId();
//        entity = upgradeService.findById(upgradeId);                
//    }
    
    public void updateUpgrade() {
        entity.setIsCompleted(Boolean.TRUE);
        upgradeService.update(entity);

        Status status = statusService.findById(StatusEnum.CLOSED.getValue());
            for (Task task : getTasks()) {
                
                if (task.getStatus().getId() != StatusEnum.CLOSED.getValue()) {                    
                    task.setStatus(status);
                    taskService.update(task);
                    notificationService.addTaskNotification(task, EventTypeEnum.CLOSED, getLoggedHelpdeskUser());   
                }                
            }      
                
        notificationService.addUpgradeNotification(entity, getLoggedUser());
    }
        
    public List<TaskNote> getPublicUpgradeNotes() {        
        return taskNoteService.getNotes(entity, NoteTypeEnum.UPGRADE_PUBLIC.getValue());        
    }
    
    public List<TaskNote> getPrivateUpgradeNotes() {
        return taskNoteService.getNotes(entity, NoteTypeEnum.UPGRADE_PRIVATE.getValue());
    }

    public Upgrade getEntity() {
        return entity;
    }

    public void setEntity(Upgrade entity) {
        this.entity = entity;
    }            
}
