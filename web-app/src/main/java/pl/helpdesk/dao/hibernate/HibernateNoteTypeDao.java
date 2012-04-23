/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import org.springframework.stereotype.Repository;
import pl.helpdesk.dao.NoteTypeDao;
import pl.helpdesk.model.NoteType;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Repository
public class HibernateNoteTypeDao extends HibernateDao<NoteType, Integer> implements NoteTypeDao {
    
    public HibernateNoteTypeDao() {
        super(NoteType.class);
    }
}
