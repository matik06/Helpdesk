/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.DefaultCustomerPriorityDao;
import pl.helpdesk.model.DefaultCustomerPriority;
import pl.helpdesk.service.DefaultCustomerPriorityService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
public class DefaultCustomerPriorityServiceImpl extends GenericServiceImpl<DefaultCustomerPriority, Integer, DefaultCustomerPriorityDao> implements DefaultCustomerPriorityService {
    @Autowired
    DefaultCustomerPriorityDao defaultCustomerPriorityDao;

    @Override
    public DefaultCustomerPriorityDao getDao() {
        return this.defaultCustomerPriorityDao;
    }
}