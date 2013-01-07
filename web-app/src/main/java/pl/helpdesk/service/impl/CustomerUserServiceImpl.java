/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.helpdesk.constant.RoleEnum;
import pl.helpdesk.dao.CustomerUserDao;
import pl.helpdesk.model.Customer;
import pl.helpdesk.model.CustomerUser;
import pl.helpdesk.model.Role;
import pl.helpdesk.service.CustomerUserService;
import pl.helpdesk.service.RoleService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS)
public class CustomerUserServiceImpl extends GenericServiceImpl<CustomerUser, Integer, CustomerUserDao> implements CustomerUserService {
    @Autowired
    CustomerUserDao customerUserDao;
    @Autowired
    RoleService roleService;

    @Override
    public CustomerUserDao getDao() {
        return this.customerUserDao;
    }

    @Override
    @Transactional(readOnly=true)
    public CustomerUser getByLogin(String login) {
        Criterion c = Restrictions.eq("login", login);
        return super.findByRestrictions(c);
    }
    
    @Override
    public List<CustomerUser> getProjectManagerList(Customer customer) {

        Session session = getDao().getSession();
        Query query = session.getNamedQuery("customerPMList");

        query.setEntity("customerId", customer);
        Role helpdeskPMRole = roleService.findById(RoleEnum.CUSTOMER_MANAGER.getValue());
        query.setEntity("roleId", helpdeskPMRole);

        return query.list();
    }
    
    @Override
    @Transactional
    public List<CustomerUser> getCustomerUsers(Customer customer) {                        
        
        Session session = getDao().getSession();
        Query query = session.getNamedQuery("customerUsers");
        query.setEntity("customerId", customer);

        return query.list();
    }
}