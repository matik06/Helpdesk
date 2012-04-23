/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import org.springframework.stereotype.Repository;
import pl.helpdesk.dao.EventDao;
import pl.helpdesk.model.Event;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Repository
public class HibernateEventDao extends HibernateDao<Event, Integer> implements EventDao {
    
    public HibernateEventDao() {
        super(Event.class);
    }
}
