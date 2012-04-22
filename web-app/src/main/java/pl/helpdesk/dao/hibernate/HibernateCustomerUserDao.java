/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import org.springframework.stereotype.Repository;
import pl.helpdesk.dao.CustomerUserDao;
import pl.helpdesk.model.CustomerUser;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Repository
public class HibernateCustomerUserDao extends HibernateDao<CustomerUser, Integer> implements CustomerUserDao {
    
    public HibernateCustomerUserDao() {
        super(CustomerUser.class);
    }
}