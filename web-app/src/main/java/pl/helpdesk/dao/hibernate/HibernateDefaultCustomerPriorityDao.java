/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import pl.helpdesk.dao.DefaultCustomerPriorityDao;
import pl.helpdesk.model.DefaultCustomerPriority;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class HibernateDefaultCustomerPriorityDao extends HibernateDao<DefaultCustomerPriority, Integer> implements DefaultCustomerPriorityDao {
    
    public HibernateDefaultCustomerPriorityDao() {
        super(DefaultCustomerPriority.class);
    }
}