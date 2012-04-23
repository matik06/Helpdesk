/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import org.springframework.stereotype.Repository;
import pl.helpdesk.dao.HelpdeskUserDao;
import pl.helpdesk.model.HelpdeskUser;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Repository
public class HibernateHelpdeskUserDao extends HibernateDao<HelpdeskUser, Integer> implements HelpdeskUserDao {
    
    public HibernateHelpdeskUserDao() {
        super(HelpdeskUser.class);
    }
}