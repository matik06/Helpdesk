/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.helpdesk.dao.HelpdeskUserDao;
import pl.helpdesk.model.HelpdeskUser;
import pl.helpdesk.service.HelpdeskUserService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service("HelpdeskUserService")
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS)
public class HelpdeskUserServiceImpl extends GenericServiceImpl<HelpdeskUser, Integer, HelpdeskUserDao> implements HelpdeskUserService {
    @Autowired
    HelpdeskUserDao helpdeskUserDao;

    @Override
    public HelpdeskUserDao getDao() {
        return this.helpdeskUserDao;
    }

    @Override
    @Transactional(readOnly=true)
    public HelpdeskUser getByLogin(String login) {
        Criterion c = Restrictions.eq("login", login);
        return super.findByRestrictions(c);
    }
}
