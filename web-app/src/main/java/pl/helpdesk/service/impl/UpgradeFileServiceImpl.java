/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.UpgradeFileDao;
import pl.helpdesk.model.UpgradeFile;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
public class UpgradeFileServiceImpl extends GenericServiceImpl<UpgradeFile, Integer, UpgradeFileDao> {
    @Autowired
    UpgradeFileDao upgradeFileDao;

    @Override
    public UpgradeFileDao getDao() {
        return this.upgradeFileDao;
    }
}
