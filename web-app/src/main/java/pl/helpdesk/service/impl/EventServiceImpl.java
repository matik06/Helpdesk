/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.EventDao;
import pl.helpdesk.model.Event;
import pl.helpdesk.service.EventService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS)
public class EventServiceImpl extends GenericServiceImpl<Event, Integer, EventDao> implements EventService {
    @Autowired
    EventDao eventDao;

    @Override
    public EventDao getDao() {
        return this.eventDao;
    }
}