/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller;


import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import pl.helpdesk.constant.NoteTypeEnum;
import pl.helpdesk.model.NoteType;
import pl.helpdesk.model.Task;
import pl.helpdesk.model.TaskNote;
import pl.helpdesk.service.NoteTypeService;
import pl.helpdesk.service.TaskNoteService;
import pl.helpdesk.service.TaskService;
import pl.helpdesk.util.NoteFilter;

/**
 *
 * @author matik06
 */
@Controller
@Scope(value = "view")
public class TaskDetailController extends BaseController {
    
    private static final Logger logger = Logger.getLogger(TaskDetailController.class);
    
    @Autowired
    private TaskService taskService;
    @Autowired 
    private TaskNoteService taskNoteService;
    @Autowired 
    NoteTypeService noteTypeService;
    
    private Task task;
    private TaskNote note;
    private TaskNote privateNote;
    private TaskNote upgradeNote;
    private TaskNote privateUpgradeNote;
    
    
    @PostConstruct
    public void init() throws InstantiationException, IllegalAccessException {
        int taskId = getRequestParameterAsInt("taskId");
        task = taskService.findById(taskId);
        note = new TaskNote();
        privateNote = new TaskNote();
        upgradeNote = new TaskNote();
        privateUpgradeNote = new TaskNote();
    }
    
    private void reload() {
        logger.info("reloading view :)");
        int taskId = task.getId();
        task = taskService.findById(taskId);
        note = new TaskNote();
        privateNote = new TaskNote();
        upgradeNote = new TaskNote();
        privateUpgradeNote = new TaskNote();
    }
    
    public void updateTask() {  
        taskService.update(task);        
    }
    
    
    public void saveOrUpdatePublicNote() {
        saveOrUpdateNote(note, NoteTypeEnum.PUBLIC);
    }
    
    public void saveOrUpdatePrivateNote() {
        saveOrUpdateNote(privateNote, NoteTypeEnum.PRIVATE);
    }
    
    public void saveOrUpdatePublicUpgrateNote() {
        saveOrUpdateNote(upgradeNote, NoteTypeEnum.UPGRADE_PUBLIC);
    }
    
    public void saveOrUpdatePrivateUpgrateNote() {
        saveOrUpdateNote(privateUpgradeNote, NoteTypeEnum.UPGRADE_PRIVATE);
    }
    
    private void saveOrUpdateNote(TaskNote note, NoteTypeEnum typeEnum) {
        
        logger.info("zapisujemy komentarz");
        logger.info(note);
        logger.info("koniec komentarza");
        
        NoteType type = noteTypeService.findById(typeEnum.getValue());        
        
        if (note.getId() == null) {
            note.setType(type);            
            note.setTask(task);
            note.setUser(getLoggedUser());
            note.setDate(new Date());
            note = taskNoteService.save(note);
        } else {
            taskNoteService.update(note);
        }
        
        reload();
    }
    
    //gettery i settery
    
    public List<TaskNote> getPublicNotes() {
        NoteFilter filter = new NoteFilter(task.getNotes());
        return filter.getNotes(NoteTypeEnum.PUBLIC);
    }
    
    public List<TaskNote> getPrivateNotes() {
        NoteFilter filter = new NoteFilter(task.getNotes());
        return filter.getNotes(NoteTypeEnum.PRIVATE);
    }
    
    public List<TaskNote> getPublicUpgradeNotes() {
        NoteFilter filter = new NoteFilter(task.getNotes());
        return filter.getNotes(NoteTypeEnum.UPGRADE_PUBLIC);
    }
    
    public List<TaskNote> getPrivateUpgradeNotes() {
        NoteFilter filter = new NoteFilter(task.getNotes());
        return filter.getNotes(NoteTypeEnum.UPGRADE_PRIVATE);
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public TaskNote getNote() {
        return note;
    }

    public void setNote(TaskNote note) {
        this.note = note;
    }

    public TaskNote getPrivateNote() {
        return privateNote;
    }

    public void setPrivateNote(TaskNote privateNote) {
        this.privateNote = privateNote;
    }

    public TaskNote getPrivateUpgradeNote() {
        return privateUpgradeNote;
    }

    public void setPrivateUpgradeNote(TaskNote privateUpgradeNote) {
        this.privateUpgradeNote = privateUpgradeNote;
    }

    public TaskNote getUpgradeNote() {
        return upgradeNote;
    }

    public void setUpgradeNote(TaskNote upgradeNote) {
        this.upgradeNote = upgradeNote;
    }    
}
