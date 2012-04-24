/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.stereotype.Service;
import pl.helpdesk.dao.RoleDao;
import pl.helpdesk.model.Role;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
public class RoleServiceImpl extends GenericServiceImpl<Role, Integer, RoleDao> {
}
