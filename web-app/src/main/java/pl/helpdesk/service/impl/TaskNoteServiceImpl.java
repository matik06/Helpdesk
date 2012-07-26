/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.TaskNoteDao;
import pl.helpdesk.model.TaskNote;
import pl.helpdesk.service.TaskNoteService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
public class TaskNoteServiceImpl extends GenericServiceImpl<TaskNote, Integer, TaskNoteDao> implements TaskNoteService {
    @Autowired
    TaskNoteDao taskNoteDao;

    @Override
    public TaskNoteDao getDao() {
        return this.taskNoteDao;
    }
}
