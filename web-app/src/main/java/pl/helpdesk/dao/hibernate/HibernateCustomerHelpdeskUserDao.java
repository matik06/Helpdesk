/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import pl.helpdesk.dao.CustomerHelpdeskUserDao;
import pl.helpdesk.model.CustomerHelpdeskUser;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class HibernateCustomerHelpdeskUserDao extends HibernateDao<CustomerHelpdeskUser, Integer> implements CustomerHelpdeskUserDao {
    
    public HibernateCustomerHelpdeskUserDao() {
        super(CustomerHelpdeskUser.class);
    }
}