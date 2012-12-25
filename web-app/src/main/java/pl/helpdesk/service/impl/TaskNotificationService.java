/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.helpdesk.constant.EventTypeEnum;
import pl.helpdesk.mail.MailService;
import pl.helpdesk.model.Customer;
import pl.helpdesk.model.CustomerUser;
import pl.helpdesk.model.Event;
import pl.helpdesk.model.EventType;
import pl.helpdesk.model.HelpdeskUser;
import pl.helpdesk.model.Task;
import pl.helpdesk.model.User;
import pl.helpdesk.service.CustomerHelpdeskUserService;
import pl.helpdesk.service.CustomerUserService;
import pl.helpdesk.service.EventService;
import pl.helpdesk.service.EventTypeService;
import pl.helpdesk.util.RecipientUtil;

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
    
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false, rollbackFor={Exception.class})
    public void addTaskNotification(Task task, EventTypeEnum eventType, User user) {
        Event event = addEvent(eventType, task, user);
        
        List<? extends User> PMList = getContractPMList(user, task);       
        RecipientUtil recipients = new RecipientUtil();
        recipients.add(PMList);
        recipients.add(user);
        recipients.add(task.getAuthor());
        recipients.add(task.getResponsible());
        recipients.add(task.getAuthor2());
                                
        sendMail(event, recipients.getRecipientList());
    }
    
    private List<? extends User> getContractPMList(User user, Task task) {
                        
        List<? extends User> result = new ArrayList<>();
        Customer customer = task.getCustomer();
        
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
    
    private void sendMail(Event event, List<? extends User> customerPmList) {
        
        Task task = event.getTask();        
        mailService.sendMail(customerPmList, event.getType().getName(), event.getType().getName());                
    }
}
