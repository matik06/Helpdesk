/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.helpdesk.constant.EventTypeEnum;
import pl.helpdesk.constant.NoteTypeEnum;
import pl.helpdesk.mail.MailService;
import pl.helpdesk.model.Customer;
import pl.helpdesk.model.CustomerUser;
import pl.helpdesk.model.Event;
import pl.helpdesk.model.EventType;
import pl.helpdesk.model.HelpdeskUser;
import pl.helpdesk.model.Task;
import pl.helpdesk.model.Upgrade;
import pl.helpdesk.model.User;
import pl.helpdesk.service.CustomerHelpdeskUserService;
import pl.helpdesk.service.CustomerUserService;
import pl.helpdesk.service.EventService;
import pl.helpdesk.service.EventTypeService;
import pl.helpdesk.service.TaskNoteService;
import pl.helpdesk.service.TaskService;
import pl.helpdesk.util.EmailBuilder;
import pl.helpdesk.util.RecipientUtil;
import pl.helpdesk.util.TaskEmailBuilder;
import pl.helpdesk.util.UpgradeTaskEmailBuilder;

/**
 *
 * @author matik06
 */
@Service    
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS)
//implements Serializable - jak dodam obsluge serializacji to z niewiadomych przyczyn spring nie dodaje obiektu do kontenera i nie jest on widoczny
public class TaskNotificationService implements Serializable {
    
    @Autowired
    private MailService mailService;
    @Autowired
    private EventService eventService;
    @Autowired
    private EventTypeService eventTypeService;
    @Autowired
    private CustomerHelpdeskUserService customerHelpdeskUserService;
    @Autowired
    private CustomerUserService customerUserService;
    @Autowired 
    TaskService taskService;
    @Autowired
    TaskNoteService taskNoteService;
    
    
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false, rollbackFor={Exception.class})
    public void addUpgradeNotification(Upgrade upgrade, User user) {
        
        RecipientUtil recipients = new RecipientUtil();
        
        recipients.add(customerHelpdeskUserService.getProjectManagerList(upgrade.getCustomer()));
        recipients.add(customerUserService.getProjectManagerList(upgrade.getCustomer()));
        
        recipients.add(upgrade.getCustomer().getEmail());
        recipients.add(user);
                
        EmailBuilder builder = new UpgradeTaskEmailBuilder(upgrade, taskService.findByUpgrade(upgrade), taskNoteService.getNotes(upgrade, NoteTypeEnum.UPGRADE_PUBLIC.getValue()));  
        
        senTaskdMail(builder, recipients);
    }
    
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false, rollbackFor={Exception.class})
    public void addTaskNotification(Task task, EventTypeEnum eventType, User user) {
        Event event = addEvent(eventType, task, user);
        
        List<? extends User> PMList = getContractPMList(user, task.getCustomer());       
        RecipientUtil recipients = new RecipientUtil();
        recipients.add(PMList);
        recipients.add(user);
        recipients.add(task.getAuthor());
        recipients.add(task.getResponsible());
        recipients.add(task.getAuthor2());

        EmailBuilder builder = new TaskEmailBuilder(task, event);
        senTaskdMail(builder, recipients);
    }
    
    private List<? extends User> getContractPMList(User user, Customer customer) {
                        
        List<? extends User> result = new ArrayList<>();
        
        if (user instanceof CustomerUser) {            
            result = customerHelpdeskUserService.getProjectManagerList(customer);
        } else if(user instanceof HelpdeskUser) {
            result = customerUserService.getProjectManagerList(customer);
        }                        
        
        return result;
    }
    
    
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
    public void sendMail(User user, EventTypeEnum eventEnum, String body) {
        
        EventType eventType = eventTypeService.findById(eventEnum.getValue());        
        mailService.sendMail(user.getEmail(), eventType.getName(), body);
    }
    
    private Event addEvent(EventTypeEnum eventTypeEnum, Task task, User user) {
        Event event = new Event();
        event.setDate(new Date());
        event.setTask(task);
        event.setUser(user);
        
        EventType eventType = eventTypeService.findById(eventTypeEnum.getValue());
        event.setType(eventType);
        
        return eventService.save(event);
    }    
    
    @Transactional(readOnly=true)
    private void senTaskdMail(EmailBuilder builder, RecipientUtil recipients) {                
        mailService.sendMail(recipients.getRecipientList(), builder.getTitle(), builder.getContent());
    }
}
