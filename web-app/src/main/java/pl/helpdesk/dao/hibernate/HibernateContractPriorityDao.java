/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import pl.helpdesk.dao.ContractPriorityDao;
import pl.helpdesk.model.ContractPriority;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class HibernateContractPriorityDao extends HibernateDao<ContractPriority, Integer> implements ContractPriorityDao {
    
    public HibernateContractPriorityDao() {
        super(ContractPriority.class);
    }
}