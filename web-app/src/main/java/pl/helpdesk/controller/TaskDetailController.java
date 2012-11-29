/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller;


import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import pl.helpdesk.constant.EventTypeEnum;
import pl.helpdesk.constant.NoteTypeEnum;
import pl.helpdesk.constant.StatusEnum;
import pl.helpdesk.model.NoteType;
import pl.helpdesk.model.Status;
import pl.helpdesk.model.Task;
import pl.helpdesk.model.TaskFile;
import pl.helpdesk.model.TaskNote;
import pl.helpdesk.model.User;
import pl.helpdesk.service.*;
import pl.helpdesk.service.impl.TaskNotificationService;
import pl.helpdesk.util.NoteFilter;
import pl.helpdesk.util.HelpdeskFileUtil;


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
    @Autowired
    HelpdeskFileUtil fileUtil;
    @Autowired
    TaskFileService taskFileService;
    @Autowired
    TaskNotificationService notificationService; 
    @Autowired
    StatusService taskStatusService ;
    
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
        notificationService.addTaskNotification(task, EventTypeEnum.EDIT_TASK, getLoggedUser());
        reload();
    }
    
    public void assignTaskTo() {
        taskService.update(task);
        reload();
    }
    
    
    public void saveOrUpdatePublicNote() {
        saveOrUpdateNote(note, NoteTypeEnum.PUBLIC);
        notificationService.addTaskNotification(task, EventTypeEnum.ADD_COMMENT, getLoggedUser());
        reload();
    }
    
    public void saveOrUpdatePrivateNote() {
        saveOrUpdateNote(privateNote, NoteTypeEnum.PRIVATE);
       notificationService.sendMail(getLoggedUser(), EventTypeEnum.ADD_PRIVATE_COMMENT, privateNote.getBody());
       reload();
    }
    
    public void saveOrUpdatePublicUpgrateNote() {
        saveOrUpdateNote(upgradeNote, NoteTypeEnum.UPGRADE_PUBLIC);
        notificationService.addTaskNotification(task, EventTypeEnum.ADD_UPGRADE_COMMENT, getLoggedUser());
        reload();
    }
    
    public void saveOrUpdatePrivateUpgrateNote() {
        saveOrUpdateNote(privateUpgradeNote, NoteTypeEnum.UPGRADE_PRIVATE);
        notificationService.sendMail(getLoggedUser(), EventTypeEnum.ADD_PRIVATE_UPGRADE_COMMENT, privateNote.getBody());
        reload();
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
    }
    
    public void start() {
        Status inProgress = taskStatusService.findById(StatusEnum.IN_PROGRESS.getValue());
        task.setStatus(inProgress);        
        
        if (task.getResponsible() == null) {
            task.setResponsible(getLoggedHelpdeskUser());
        }
        
        taskService.update(task);
        
        notificationService.addTaskNotification(task, EventTypeEnum.STARTED_WORKING_OVER_TASK, getLoggedUser());
        reload();
    }
    
    public void setReadyForUpgrade() {
        Status inProgress = taskStatusService.findById(StatusEnum.READY_FOR_UPGRADE.getValue());
        task.setStatus(inProgress);
        taskService.update(task);

        notificationService.addTaskNotification(task, EventTypeEnum.FINISHED_READY_FOR_UPGRADE, getLoggedUser());
        reload();
    }
    
    public void close() {
        Status inProgress = taskStatusService.findById(StatusEnum.CLOSED.getValue());
        task.setStatus(inProgress);
        taskService.update(task);

        notificationService.addTaskNotification(task, EventTypeEnum.CLOSED, getLoggedUser());
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
    
    public void upload(FileUploadEvent event) {
        
        UploadedFile file = event.getFile();
        String extension = FilenameUtils.getExtension(file.getFileName());                
        String generatedFile = null;
        
        try {
            generatedFile = fileUtil.upload(file.getInputstream(), extension);                                                
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
        
        TaskFile taskFile = new TaskFile();
        taskFile.setDate(new Date());
        taskFile.setName(file.getFileName());
        taskFile.setFileSystemName(generatedFile);
        taskFile.setTask(task);
        taskFile.setUser(getLoggedUser());
        taskFile.setContentType(file.getContentType());
        
        taskFileService.save(taskFile);        
        reload();
    }            
    
    public boolean canEdit() {
        User loggedUser = getLoggedUser();
        
        if (task.canEdit(loggedUser)) {
            return true;
        } else {
            return false;
        }
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
