/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.helpdesk.constant.RoleEnum;
import pl.helpdesk.dao.CustomerHelpdeskUserDao;
import pl.helpdesk.model.Customer;
import pl.helpdesk.model.CustomerHelpdeskUser;
import pl.helpdesk.model.HelpdeskUser;
import pl.helpdesk.model.Role;
import pl.helpdesk.service.CustomerHelpdeskUserService;
import pl.helpdesk.service.RoleService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS)
public class CustomerHelpdeskUserServiceImpl extends GenericServiceImpl<CustomerHelpdeskUser, Integer, CustomerHelpdeskUserDao> implements CustomerHelpdeskUserService {
    
    @Autowired
    CustomerHelpdeskUserDao customerHelpdeskUserDao;
    @Autowired
    RoleService roleService;

    @Override
    public CustomerHelpdeskUserDao getDao() {
        return customerHelpdeskUserDao;
    }

    @Override
    public List<HelpdeskUser> getProjectManagerList(Customer customer) {                
        
        Session session = getDao().getSession();
        Query query = session.getNamedQuery("helpdeskPMList");
        
        query.setEntity("customerId", customer);
        Role helpdeskPMRole = roleService.findById(RoleEnum.HELPDESK_MANAGER.getValue());
        query.setEntity("roleId", helpdeskPMRole);
        
        return query.list();        
    }
    
}