/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.EventDao;
import pl.helpdesk.model.Event;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
public class EventServiceImpl extends GenericServiceImpl<Event, Integer, EventDao> {
    @Autowired
    EventDao eventDao;

    @Override
    public EventDao getDao() {
        return this.eventDao;
    }
}