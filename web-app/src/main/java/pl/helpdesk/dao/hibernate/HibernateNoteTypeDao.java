/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import pl.helpdesk.dao.NoteTypeDao;
import pl.helpdesk.model.NoteType;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class HibernateNoteTypeDao extends HibernateDao<NoteType, Integer> implements NoteTypeDao {
    
    public HibernateNoteTypeDao() {
        super(NoteType.class);
    }
}
