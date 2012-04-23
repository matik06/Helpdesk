/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import org.springframework.stereotype.Repository;
import pl.helpdesk.dao.TaskFileDao;
import pl.helpdesk.model.TaskFile;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Repository
public class HibernateTaskFileDao extends HibernateDao<TaskFile, Integer> implements TaskFileDao {
    
    public HibernateTaskFileDao() {
        super(TaskFile.class);
    }
}
