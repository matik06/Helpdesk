/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.HelpdeskPrivilageDao;
import pl.helpdesk.model.HelpdeskPrivilage;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
public class HelpdeskPrivilageServiceImpl extends GenericServiceImpl<HelpdeskPrivilage, Integer, HelpdeskPrivilageDao> {
    @Autowired
    HelpdeskPrivilageDao helpdeskPrivilageDao;

    @Override
    public HelpdeskPrivilageDao getDao() {
        return this.helpdeskPrivilageDao;
    }
}
