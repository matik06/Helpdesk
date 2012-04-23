/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import org.springframework.stereotype.Repository;
import pl.helpdesk.dao.UpgradeDao;
import pl.helpdesk.model.Upgrade;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Repository
public class HibernateUpgradeDao extends HibernateDao<Upgrade, Integer> implements UpgradeDao {
    
    public HibernateUpgradeDao() {
        super(Upgrade.class);
    }
}