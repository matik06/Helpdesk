/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import java.util.List;
import org.springframework.stereotype.Repository;
import pl.helpdesk.dao.CustomerDao;
import pl.helpdesk.model.Customer;
import pl.helpdesk.model.CustomerHelpdeskUser;
import pl.helpdesk.model.HelpdeskUser;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Repository
public class HibernateCustomerDao extends HibernateDao<Customer, Integer> implements CustomerDao {
    
    public HibernateCustomerDao() {
        super(Customer.class);
    }

    @Override
    public List<Customer> findAll(HelpdeskUser helpdeskUser) {
        String query = "SELECT distinct customer from " + CustomerHelpdeskUser.class.getName() 
                + " where helpdeskUser = :user";
        
        return getSession().createQuery(query)
                .setParameter("user", helpdeskUser).list();
    }
}
