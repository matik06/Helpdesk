/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.UpgradeNoteDao;
import pl.helpdesk.model.UpgradeNote;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
public class UpgradeNoteServiceImpl extends GenericServiceImpl<UpgradeNote, Integer, UpgradeNoteDao> {
    @Autowired
    UpgradeNoteDao upgradeNoteDao;

    @Override
    public UpgradeNoteDao getDao() {
        return this.upgradeNoteDao;
    }
}
