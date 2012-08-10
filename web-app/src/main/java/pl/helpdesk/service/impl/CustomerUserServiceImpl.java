/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.CustomerUserDao;
import pl.helpdesk.model.CustomerUser;
import pl.helpdesk.service.CustomerUserService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS)
public class CustomerUserServiceImpl extends GenericServiceImpl<CustomerUser, Integer, CustomerUserDao> implements CustomerUserService {
    @Autowired
    CustomerUserDao customerUserDao;

    @Override
    public CustomerUserDao getDao() {
        return this.customerUserDao;
    }
}