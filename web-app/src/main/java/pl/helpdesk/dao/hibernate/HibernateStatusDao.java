/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import pl.helpdesk.dao.StatusDao;
import pl.helpdesk.model.Status;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class HibernateStatusDao extends HibernateDao<Status, Integer> implements StatusDao {
    
    public HibernateStatusDao() {
        super(Status.class);
    }
}