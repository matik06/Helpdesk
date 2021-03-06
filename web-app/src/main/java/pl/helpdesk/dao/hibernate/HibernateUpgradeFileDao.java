/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import org.springframework.stereotype.Repository;
import pl.helpdesk.dao.UpgradeFileDao;
import pl.helpdesk.model.UpgradeFile;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Repository
public class HibernateUpgradeFileDao extends HibernateDao<UpgradeFile, Integer> implements UpgradeFileDao {
    
    public HibernateUpgradeFileDao() {
        super(UpgradeFile.class);
    }
}