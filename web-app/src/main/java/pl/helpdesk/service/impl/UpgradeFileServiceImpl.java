/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.UpgradeFileDao;
import pl.helpdesk.model.UpgradeFile;
import pl.helpdesk.service.UpgradeFileService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS)
public class UpgradeFileServiceImpl extends GenericServiceImpl<UpgradeFile, Integer, UpgradeFileDao> implements UpgradeFileService {
    @Autowired
    UpgradeFileDao upgradeFileDao;

    @Override
    public UpgradeFileDao getDao() {
        return this.upgradeFileDao;
    }
}
