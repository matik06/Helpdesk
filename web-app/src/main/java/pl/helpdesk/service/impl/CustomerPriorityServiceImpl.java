/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.CustomerPriorityDao;
import pl.helpdesk.model.CustomerPriority;
import pl.helpdesk.service.CustomerPriorityService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS)
public class CustomerPriorityServiceImpl extends GenericServiceImpl<CustomerPriority, Integer, CustomerPriorityDao> implements CustomerPriorityService {
    
    @Autowired 
    CustomerPriorityDao customerPriorityDao;

    @Override
    public CustomerPriorityDao getDao() {
        return this.customerPriorityDao;
    }
    
}