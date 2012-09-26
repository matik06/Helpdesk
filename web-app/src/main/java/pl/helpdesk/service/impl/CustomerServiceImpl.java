/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.helpdesk.dao.CustomerDao;
import pl.helpdesk.model.Customer;
import pl.helpdesk.model.CustomerHelpdeskUser;
import pl.helpdesk.model.HelpdeskUser;
import pl.helpdesk.service.CustomerService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS)
@Transactional(readOnly=true)
public class CustomerServiceImpl extends GenericServiceImpl<Customer, Integer, CustomerDao> implements CustomerService {
    @Autowired
    CustomerDao customerDao;

    @Override
    public CustomerDao getDao() {
        return customerDao;
    }

    @Override
    public List<Customer> findAll(HelpdeskUser helpdeskUser) {                
        return customerDao.findAll(helpdeskUser);        
    }
}
