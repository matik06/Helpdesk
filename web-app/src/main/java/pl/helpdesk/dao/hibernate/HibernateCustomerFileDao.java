/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import org.springframework.stereotype.Repository;
import pl.helpdesk.dao.CustomerFileDao;
import pl.helpdesk.model.CustomerFile;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Repository
public class HibernateCustomerFileDao extends HibernateDao<CustomerFile, Integer> implements CustomerFileDao {
    
    public HibernateCustomerFileDao() {
        super(CustomerFile.class);
    }
}
