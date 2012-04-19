/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import org.springframework.stereotype.Repository;
import pl.helpdesk.dao.ContractDao;
import pl.helpdesk.model.Contract;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Repository
public class HibernateContractDao extends HibernateDao<Contract, Integer> implements ContractDao {
    
    public HibernateContractDao() {
        super(Contract.class);
    }
}
