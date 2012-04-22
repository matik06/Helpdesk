/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import pl.helpdesk.dao.UpgradeDao;
import pl.helpdesk.model.Upgrade;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class HibernateUpgradeDao extends HibernateDao<Upgrade, Integer> implements UpgradeDao {
    
    public HibernateUpgradeDao() {
        super(Upgrade.class);
    }
}