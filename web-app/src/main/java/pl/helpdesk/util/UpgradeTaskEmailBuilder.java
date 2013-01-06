/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.util;

import java.util.List;
import pl.helpdesk.model.Task;
import pl.helpdesk.model.TaskNote;
import pl.helpdesk.model.Upgrade;

/**
 *
 * @author mlubanski
 */
public class UpgradeTaskEmailBuilder implements EmailBuilder {
    
    private Upgrade upgrade;
    private List<Task> tasks;
    private List<TaskNote> upgradeNotes;
    
    static final String NEW_LINE = "<br /><br />";
    static final String CONTEXT = "https://localhost:8443/helpdesk/";
    
    static final String getUrl(String name, String path) {
        StringBuilder result = new StringBuilder();
        
        result.append("<a href=\"").append(CONTEXT).append(path).append("\">")
                .append(name).append("</a>");
        
        return result.toString();
    }
    
    static final String getUpgradeUrl(Upgrade upgrade) {
        String path = "update/" + upgrade.getId() + "/detail";
        return getUrl(upgrade.getDescription(), path);
    }
    
    static final String getTaskUrl(Task task) {
        String path = "task/" + task.getId() + "/detail";
        return getUrl(task.getTitle(), path);
    }
    
    
    public UpgradeTaskEmailBuilder(Upgrade upgrade, List<Task> tasks, List<TaskNote> upgradeNotes) {
        this.upgrade = upgrade;
        this.tasks = tasks;
        this.upgradeNotes = upgradeNotes;
    }

    @Override
    public String getTitle() {
        return "Aktualizacja";
    }

    @Override
    public String getContent() {
        
        StringBuilder result = new StringBuilder();
        
        result.append("<b>W sklad aktualizacji " + getUpgradeUrl(upgrade) + " wchodza nastepujace zgloszenia:</b> ");
        result.append(NEW_LINE);
        
        for (Task task : tasks) {
            result.append(getTaskUrl(task));
            result.append(NEW_LINE);
        }
        
        result.append("<b>Komentarze:</b> ").append(NEW_LINE);        
        for (TaskNote note : upgradeNotes) {
            result.append(note.getBody());
            result.append(NEW_LINE);
        }
                     
        
        result.append("-------------------------").append("<br />");
        result.append("Wygenerowane automatycznie przez system Helpdesk");
        
        return result.toString();        
    }
    
}
