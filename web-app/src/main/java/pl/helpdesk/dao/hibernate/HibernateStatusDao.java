/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import org.springframework.stereotype.Repository;
import pl.helpdesk.dao.StatusDao;
import pl.helpdesk.model.Status;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Repository
public class HibernateStatusDao extends HibernateDao<Status, Integer> implements StatusDao {
    
    public HibernateStatusDao() {
        super(Status.class);
    }
}