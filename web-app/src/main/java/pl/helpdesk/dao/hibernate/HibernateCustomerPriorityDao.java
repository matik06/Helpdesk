/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import org.springframework.stereotype.Repository;
import pl.helpdesk.dao.CustomerPriorityDao;
import pl.helpdesk.model.CustomerPriority;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Repository
public class HibernateCustomerPriorityDao extends HibernateDao<CustomerPriority, Integer> implements CustomerPriorityDao {
    
    public HibernateCustomerPriorityDao() {
        super(CustomerPriority.class);
    }
}