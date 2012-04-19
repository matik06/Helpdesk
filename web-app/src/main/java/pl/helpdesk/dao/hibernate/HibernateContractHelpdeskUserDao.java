/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import pl.helpdesk.dao.ContractHelpdeskUserDao;
import pl.helpdesk.model.ContractHelpdeskUser;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class HibernateContractHelpdeskUserDao extends HibernateDao<ContractHelpdeskUser, Integer> implements ContractHelpdeskUserDao {
    
    public HibernateContractHelpdeskUserDao() {
        super(ContractHelpdeskUser.class);
    }
}