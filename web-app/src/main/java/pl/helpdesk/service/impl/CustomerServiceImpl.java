/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.CustomerDao;
import pl.helpdesk.model.Customer;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
public class CustomerServiceImpl extends GenericServiceImpl<Customer, Integer, CustomerDao> {
    @Autowired
    CustomerDao customerDao;

    @Override
    public CustomerDao getDao() {
        return customerDao;
    }
}
