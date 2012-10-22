/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.helpdesk.constant.NoteTypeEnum;
import pl.helpdesk.dao.TaskNoteDao;
import pl.helpdesk.model.Task;
import pl.helpdesk.model.TaskNote;
import pl.helpdesk.service.TaskNoteService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS)
public class TaskNoteServiceImpl extends GenericServiceImpl<TaskNote, Integer, TaskNoteDao> implements TaskNoteService {
    @Autowired
    TaskNoteDao taskNoteDao;

    @Override
    public TaskNoteDao getDao() {
        return this.taskNoteDao;
    }

    @Override
    @Transactional(readOnly=true)
    public List<TaskNote> getNotes(Task task, Integer noteType) {
        
        Criterion taskCriterion = Restrictions.eq("task", task);
        Criterion noteTypeCriterion = Restrictions.eq("type.id", noteType);
        
        return taskNoteDao.findAllByRestriction(taskCriterion, noteTypeCriterion);
    }
}
