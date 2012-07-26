/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.NoteTypeDao;
import pl.helpdesk.model.NoteType;
import pl.helpdesk.service.NoteTypeService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
public class NoteTypeServiceImpl extends GenericServiceImpl<NoteType, Integer, NoteTypeDao> implements NoteTypeService {
    @Autowired
    NoteTypeDao noteTypeDao;

    @Override
    public NoteTypeDao getDao() {
        return this.noteTypeDao;
    }
}
