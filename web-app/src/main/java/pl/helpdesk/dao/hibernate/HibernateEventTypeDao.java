/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import pl.helpdesk.dao.EventTypeDao;
import pl.helpdesk.model.EventType;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class HibernateEventTypeDao extends HibernateDao<EventType, Integer> implements EventTypeDao {
    
    public HibernateEventTypeDao() {
        super(EventType.class);
    }
}