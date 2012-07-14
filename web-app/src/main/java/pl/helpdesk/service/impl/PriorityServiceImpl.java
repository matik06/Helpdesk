/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.PriorityDao;
import pl.helpdesk.model.Priority;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
public class PriorityServiceImpl extends GenericServiceImpl<Priority, Integer, PriorityDao> {
    @Autowired
    PriorityDao priorityDao;

    @Override
    public PriorityDao getDao() {
        return this.priorityDao;
    }
}
