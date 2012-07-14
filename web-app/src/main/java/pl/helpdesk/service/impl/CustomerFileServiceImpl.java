/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.CustomerFileDao;
import pl.helpdesk.model.CustomerFile;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
public class CustomerFileServiceImpl extends GenericServiceImpl<CustomerFile, Integer, CustomerFileDao> {
    
    @Autowired 
    CustomerFileDao customerFileDao;

    @Override
    public CustomerFileDao getDao() {
        return this.customerFileDao;
    }
}
