/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.PriorityDao;
import pl.helpdesk.model.Priority;
import pl.helpdesk.service.PriorityService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS)
public class PriorityServiceImpl extends GenericServiceImpl<Priority, Integer, PriorityDao> implements PriorityService {
    @Autowired
    PriorityDao priorityDao;

    @Override
    public PriorityDao getDao() {
        return this.priorityDao;
    }
}
