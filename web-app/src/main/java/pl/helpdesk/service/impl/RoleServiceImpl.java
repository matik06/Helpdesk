/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.RoleDao;
import pl.helpdesk.model.Role;
import pl.helpdesk.service.RoleService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
public class RoleServiceImpl extends GenericServiceImpl<Role, Integer, RoleDao> implements RoleService, Serializable {
    @Autowired
    RoleDao roleDao;

    @Override
    public RoleDao getDao() {
        return this.roleDao;
    }
}
