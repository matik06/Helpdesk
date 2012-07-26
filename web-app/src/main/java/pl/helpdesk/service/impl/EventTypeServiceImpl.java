/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.stereotype.Service;
import pl.helpdesk.dao.EventTypeDao;
import pl.helpdesk.model.EventType;
import pl.helpdesk.service.EventTypeService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
public class EventTypeServiceImpl extends GenericServiceImpl<EventType, Integer, EventTypeDao> implements EventTypeService {
    EventTypeDao eventTypeDao;

    @Override
    public EventTypeDao getDao() {
        return this.eventTypeDao;
    }
}