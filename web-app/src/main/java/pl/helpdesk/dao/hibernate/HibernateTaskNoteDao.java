/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import org.springframework.stereotype.Repository;
import pl.helpdesk.dao.TaskNoteDao;
import pl.helpdesk.model.TaskNote;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Repository
public class HibernateTaskNoteDao extends HibernateDao<TaskNote, Integer> implements TaskNoteDao {
    
    public HibernateTaskNoteDao() {
        super(TaskNote.class);
    }
}