/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.CustomerFileDao;
import pl.helpdesk.model.CustomerFile;
import pl.helpdesk.service.CustomerFileService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS)
public class CustomerFileServiceImpl extends GenericServiceImpl<CustomerFile, Integer, CustomerFileDao> implements CustomerFileService {
    
    @Autowired 
    CustomerFileDao customerFileDao;

    @Override
    public CustomerFileDao getDao() {
        return this.customerFileDao;
    }
}
