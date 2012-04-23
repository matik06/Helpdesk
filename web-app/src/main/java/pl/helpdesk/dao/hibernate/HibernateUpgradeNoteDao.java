/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import org.springframework.stereotype.Repository;
import pl.helpdesk.dao.UpgradeNoteDao;
import pl.helpdesk.model.UpgradeNote;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Repository
public class HibernateUpgradeNoteDao extends HibernateDao<UpgradeNote, Integer> implements UpgradeNoteDao {
    
    public HibernateUpgradeNoteDao() {
        super(UpgradeNote.class);
    }
}