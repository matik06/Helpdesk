/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import pl.helpdesk.dao.PriorityDao;
import pl.helpdesk.model.Priority;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class HibernatePriorityDao extends HibernateDao<Priority, Integer> implements  PriorityDao{
    
    public HibernatePriorityDao() {
        super(Priority.class);
    }
}
