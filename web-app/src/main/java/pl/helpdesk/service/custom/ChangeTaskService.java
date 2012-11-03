/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.custom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class ChangeTaskService {
    
    private MailService mailService;
    private EventService eventService;
    private EventTypeService eventTypeService;
    
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false, rollbackFor={Exception.class})
    public void changeTask(Task task, EventTypeEnum eventType, User user) {
        Event event = addEvent(eventType, task, user);
        sendMail(event);
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
        } else {
            List<User> recipients = new ArrayList();
            recipients.add(null);
            User responsible = task.getResponsible();
            User author = task.getAuthor();
            
            mailService.sendMail(recipients, event.getType().getName(), event.getType().getName());
        }
    }
}
