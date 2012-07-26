/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.StatusDao;
import pl.helpdesk.model.Status;
import pl.helpdesk.service.StatusService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
public class StatusServiceImpl extends GenericServiceImpl<Status, Integer, StatusDao> implements StatusService {
    @Autowired
    StatusDao statusDao;

    @Override
    public StatusDao getDao() {
        return this.statusDao;
    }
}
