/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.HelpdeskPrivilageDao;
import pl.helpdesk.model.HelpdeskPrivilage;
import pl.helpdesk.service.HelpdeskPrivilageService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS)
public class HelpdeskPrivilageServiceImpl extends GenericServiceImpl<HelpdeskPrivilage, Integer, HelpdeskPrivilageDao> implements HelpdeskPrivilageService {
    @Autowired
    HelpdeskPrivilageDao helpdeskPrivilageDao;

    @Override
    public HelpdeskPrivilageDao getDao() {
        return this.helpdeskPrivilageDao;
    }
}
