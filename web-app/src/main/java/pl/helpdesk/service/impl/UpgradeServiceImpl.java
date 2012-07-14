/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.UpgradeDao;
import pl.helpdesk.model.Upgrade;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
public class UpgradeServiceImpl extends GenericServiceImpl<Upgrade, Integer, UpgradeDao> {
    @Autowired
    UpgradeDao upgradeDao;

    @Override
    public UpgradeDao getDao() {
        return this.upgradeDao;
    }
}
