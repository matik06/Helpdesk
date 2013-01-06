/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.util;

import java.util.Locale;
import pl.helpdesk.model.Event;
import pl.helpdesk.model.Task;
import pl.helpdesk.model.TaskNote;

/**
 *
 * @author mlubanski
 */
public class TaskEmailBuilder implements EmailBuilder {
    
    private Task task;
    private Event event;
    
    public TaskEmailBuilder(Task task, Event event) {
        this.task = task;
        this.event = event;
    }

    @Override
    public String getTitle() {
        return event.getType().getName();
    }

    @Override
    public String getContent() {
        
        StringBuilder result = new StringBuilder();
        
        result.append("Zgloszenie " + UpgradeTaskEmailBuilder.getTaskUrl(task) + " zostalo zaktualizowane: ");
        result.append("<b>");
        result.append(event.getType().getName());
        result.append("</b>");
        result.append(UpgradeTaskEmailBuilder.NEW_LINE);
        
        result.append("-------------------------").append("<br />");
        result.append("Wygenerowane automatycznie przez system Helpdesk");
        
        return result.toString();                
    }
}
