/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import org.springframework.stereotype.Repository;
import pl.helpdesk.dao.UserDao;
import pl.helpdesk.model.User;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Repository
public class UserDaoImpl extends HibernateDao<User, Integer> implements UserDao {
    
    public UserDaoImpl() {
        super(User.class);
    }
}
