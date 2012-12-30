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
        upgradeService.update(entity);
        //TODO
//        notificationService.addUpgradeNotification(upgrade, getLoggedUser());
    }
        
    public List<TaskNote> getPublicUpgradeNotes() {
        //TODO
//        NoteTypeEnum.UPGRADE_PUBLIC;
        return null;
    }
    
    public List<TaskNote> getPrivateUpgradeNotes() {
        //TODO
//                NoteTypeEnum.UPGRADE_PRIVATE;
        return null;
    }

    public Upgrade getEntity() {
        return entity;
    }

    public void setEntity(Upgrade entity) {
        this.entity = entity;
    }            
}
