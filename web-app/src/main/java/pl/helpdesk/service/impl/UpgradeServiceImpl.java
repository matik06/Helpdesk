/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import java.util.List;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.helpdesk.dao.UpgradeDao;
import pl.helpdesk.model.Customer;
import pl.helpdesk.model.Upgrade;
import pl.helpdesk.service.UpgradeService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS)
public class UpgradeServiceImpl extends GenericServiceImpl<Upgrade, Integer, UpgradeDao> implements UpgradeService {
    @Autowired
    UpgradeDao upgradeDao;

    @Override
    public UpgradeDao getDao() {
        return this.upgradeDao;
    }

    @Override
    @Transactional(readOnly=true)
    public List<Upgrade> findAll(boolean isCompleted) {
        
        Query query = getSession().getNamedQuery("upgrades");
        query.setBoolean("isCompleted", isCompleted);

        return query.list();
    }  
    
    

    @Override
    @Transactional(readOnly=true)
    public List<Upgrade> findAll(Customer customer, boolean isCompleted) {
        
        Query query = getSession().getNamedQuery("upgradesByCustomer");
        query.setEntity("customerId", customer);
        query.setBoolean("isCompleted", isCompleted);        

        return query.list();
    }
            
}
