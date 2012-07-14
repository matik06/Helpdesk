/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.TaskNoteDao;
import pl.helpdesk.model.TaskNote;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
public class TaskNoteServiceImpl extends GenericServiceImpl<TaskNote, Integer, TaskNoteDao> {
    @Autowired
    TaskNoteDao taskNoteDao;

    @Override
    public TaskNoteDao getDao() {
        return this.taskNoteDao;
    }
}
