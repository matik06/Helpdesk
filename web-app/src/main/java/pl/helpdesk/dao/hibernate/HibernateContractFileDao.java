/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import org.springframework.stereotype.Repository;
import pl.helpdesk.dao.ContractFileDao;
import pl.helpdesk.model.ContractFile;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Repository
public class HibernateContractFileDao extends HibernateDao<ContractFile, Integer> implements ContractFileDao {
    
    public HibernateContractFileDao() {
        super(ContractFile.class);
    }
}
