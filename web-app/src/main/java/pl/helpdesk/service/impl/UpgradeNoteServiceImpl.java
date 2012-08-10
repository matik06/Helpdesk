/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.UpgradeNoteDao;
import pl.helpdesk.model.UpgradeNote;
import pl.helpdesk.service.UpgradeNoteService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS)
public class UpgradeNoteServiceImpl extends GenericServiceImpl<UpgradeNote, Integer, UpgradeNoteDao> implements UpgradeNoteService {
    @Autowired
    UpgradeNoteDao upgradeNoteDao;

    @Override
    public UpgradeNoteDao getDao() {
        return this.upgradeNoteDao;
    }
}
