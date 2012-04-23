/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import org.springframework.stereotype.Repository;
import pl.helpdesk.dao.HelpdeskPrivilageDao;
import pl.helpdesk.model.HelpdeskPrivilage;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Repository
public class HibernateHelpdeskPrivilageDao extends HibernateDao<HelpdeskPrivilage, Integer> implements HelpdeskPrivilageDao {
    
    public HibernateHelpdeskPrivilageDao() {
        super(HelpdeskPrivilage.class);
    }
}
