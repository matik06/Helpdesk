/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.CustomerPriorityDao;
import pl.helpdesk.model.CustomerPriority;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
public class CustomerPriorityServiceImpl extends GenericServiceImpl<CustomerPriority, Integer, CustomerPriorityDao> {
    
    @Autowired 
    CustomerPriorityDao customerPriorityDao;

    @Override
    public CustomerPriorityDao getDao() {
        return this.customerPriorityDao;
    }
    
}