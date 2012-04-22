/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import pl.helpdesk.dao.UpgradeNoteDao;
import pl.helpdesk.model.UpgradeNote;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class HibernateUpgradeNoteDao extends HibernateDao<UpgradeNote, Integer> implements UpgradeNoteDao {
    
    public HibernateUpgradeNoteDao() {
        super(UpgradeNote.class);
    }
}