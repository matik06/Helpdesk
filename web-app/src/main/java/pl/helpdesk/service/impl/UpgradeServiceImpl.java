/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.UpgradeDao;
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
}
