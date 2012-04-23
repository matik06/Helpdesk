/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import org.springframework.stereotype.Repository;
import pl.helpdesk.dao.PriorityDao;
import pl.helpdesk.model.Priority;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Repository
public class HibernatePriorityDao extends HibernateDao<Priority, Integer> implements  PriorityDao{
    
    public HibernatePriorityDao() {
        super(Priority.class);
    }
}
