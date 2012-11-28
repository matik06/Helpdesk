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
import pl.helpdesk.mail.MailService;
import pl.helpdesk.model.Event;
import pl.helpdesk.model.EventType;
import pl.helpdesk.model.Task;
import pl.helpdesk.model.User;
import pl.helpdesk.service.EventService;
import pl.helpdesk.service.EventTypeService;

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
    
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false, rollbackFor={Exception.class})
    public void addTaskNotification(Task task, EventTypeEnum eventType, User user) {
        Event event = addEvent(eventType, task, user);
        sendMail(event);
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
    
    private void sendMail(Event event) {
        
        Task task = event.getTask();
        
        if (event.getType().getId() == EventTypeEnum.CREATED_TASK.getValue()) {
            //przy tworzeniu taska wysyłamy maila tylko do PM odpowiedzialnych za daną umowę serwisową
            mailService.sendMail("matik06@gmail.com", "created new task :)", task.getDescription());
        } else {
            List<User> recipients = new ArrayList();
            recipients.add(task.getResponsible());
            recipients.add(task.getAuthor());            
            
            mailService.sendMail(recipients, event.getType().getName(), event.getType().getName());
        }
    }
}
