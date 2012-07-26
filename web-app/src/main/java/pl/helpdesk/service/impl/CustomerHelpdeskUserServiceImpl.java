/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.CustomerHelpdeskUserDao;
import pl.helpdesk.model.CustomerHelpdeskUser;
import pl.helpdesk.service.CustomerHelpdeskUserService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
public class CustomerHelpdeskUserServiceImpl extends GenericServiceImpl<CustomerHelpdeskUser, Integer, CustomerHelpdeskUserDao> implements CustomerHelpdeskUserService {
    
    @Autowired
    CustomerHelpdeskUserDao customerHelpdeskUserDao;

    @Override
    public CustomerHelpdeskUserDao getDao() {
        return customerHelpdeskUserDao;
    }

    
}