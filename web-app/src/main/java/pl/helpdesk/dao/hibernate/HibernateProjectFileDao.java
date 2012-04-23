/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import org.springframework.stereotype.Repository;
import pl.helpdesk.dao.ProjectFileDao;
import pl.helpdesk.model.ProjectFile;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Repository
public class HibernateProjectFileDao extends HibernateDao<ProjectFile, Integer> implements ProjectFileDao {
    
    public HibernateProjectFileDao() {
        super(ProjectFile.class);
    }
}