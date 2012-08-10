/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.CustomerDao;
import pl.helpdesk.model.Customer;
import pl.helpdesk.service.CustomerService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS)
public class CustomerServiceImpl extends GenericServiceImpl<Customer, Integer, CustomerDao> implements CustomerService {
    @Autowired
    CustomerDao customerDao;

    @Override
    public CustomerDao getDao() {
        return customerDao;
    }
}
