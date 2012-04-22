/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import org.springframework.stereotype.Repository;
import pl.helpdesk.dao.CustomerDao;
import pl.helpdesk.model.Customer;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Repository
public class HibernateCustomerDao extends HibernateDao<Customer, Integer> implements CustomerDao {
    
    public HibernateCustomerDao() {
        super(Customer.class);
    }
}
