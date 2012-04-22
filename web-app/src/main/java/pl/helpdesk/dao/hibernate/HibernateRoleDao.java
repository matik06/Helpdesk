/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import pl.helpdesk.dao.RoleDao;
import pl.helpdesk.model.Role;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class HibernateRoleDao extends HibernateDao<Role, Integer> implements RoleDao {
    
    public HibernateRoleDao() {
        super(Role.class);
    }
}
