/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import pl.helpdesk.dao.TaskDao;
import pl.helpdesk.model.Task;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class HibernateTaskDao extends HibernateDao<Task, Integer> implements TaskDao {
    
    public HibernateTaskDao() {
        super(Task.class);
    }
}