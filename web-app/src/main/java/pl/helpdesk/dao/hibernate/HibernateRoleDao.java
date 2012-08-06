/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import java.io.Serializable;
import org.springframework.stereotype.Repository;
import pl.helpdesk.dao.RoleDao;
import pl.helpdesk.model.Role;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Repository
public class HibernateRoleDao extends HibernateDao<Role, Integer> implements RoleDao, Serializable {
    
    public HibernateRoleDao() {
        super(Role.class);
    }
}
