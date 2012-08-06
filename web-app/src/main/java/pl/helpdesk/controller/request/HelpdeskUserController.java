/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller.request;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import pl.helpdesk.model.HelpdeskUser;
import pl.helpdesk.service.HelpdeskUserService;


/**
 *
 * @author matik06
 */
@Controller
@Scope(value="view")
public class HelpdeskUserController {
    
    @Autowired
    HelpdeskUserService helpdeskUserService;        
    
    private List<HelpdeskUser> entityList;
    private HelpdeskUser newEntity;
    private HelpdeskUser selectedEntity;
   
    
    
    
    
    private boolean permissionToWrite = true;
    
    
    
    @PostConstruct
    public void init() {  
        
        entityList = helpdeskUserService.findAll();
        
        newEntity = new HelpdeskUser();
        System.out.println("init()");
    }
    
    private HelpdeskUser randomUser() {
        HelpdeskUser u = new HelpdeskUser();
        
        int i = new Random().nextInt(1000);
        u.setId(i);
        u.setLogin("login " + i);
        u.setFirstName("firstName: " + i);
        u.setLastName("lastname: " + i);
        
        return u;
    }    
    
    public void addEntity() {                   
        
        helpdeskUserService.save(newEntity);
        entityList.add(newEntity);
        this.newEntity = new HelpdeskUser();
    }
    
    public void updateEntity() {
        System.out.println("update entity");
        helpdeskUserService.update(selectedEntity);
        entityList = helpdeskUserService.findAll();
    }
    
    public void deleteEntity() {
        
        System.out.println(selectedEntity);
        helpdeskUserService.delete(selectedEntity);
        entityList.remove(selectedEntity);
        selectedEntity = new HelpdeskUser();                
    }    
    
    public void process() {
        System.out.println("process selected user: ");
        System.out.println(selectedEntity);
    }
    
    public void onRowSelect(SelectEvent event) {
        this.selectedEntity = (HelpdeskUser)event.getObject();
    }       

    public boolean isPermissionToWrite() {
        return permissionToWrite;
    }

    public void enablePermissionToWrite() {
        permissionToWrite = true;
    }        
    
    public void disablePermissionToWrite() {
        permissionToWrite = false;
    }

    public HelpdeskUser getNewEntity() {
        return newEntity;
    }

    public void setNewEntity(HelpdeskUser newEntity) {
        this.newEntity = newEntity;
    }

    public HelpdeskUser getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(HelpdeskUser selectedEntity) {
        this.selectedEntity = selectedEntity;
    }

    public List<HelpdeskUser> getEntityList() {
        return entityList;
    }        
}
