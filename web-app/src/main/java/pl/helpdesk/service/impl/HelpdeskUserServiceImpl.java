/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.HelpdeskUserDao;
import pl.helpdesk.model.HelpdeskUser;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service("HelpdeskUserService")
public class HelpdeskUserServiceImpl extends GenericServiceImpl<HelpdeskUser, Integer, HelpdeskUserDao> {
    @Autowired
    HelpdeskUserDao helpdeskUserDao;

    @Override
    public HelpdeskUserDao getDao() {
        return this.helpdeskUserDao;
    }
}
