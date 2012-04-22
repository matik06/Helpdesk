/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import pl.helpdesk.dao.ProjectFileDao;
import pl.helpdesk.model.ProjectFile;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class HibernateProjectFileDao extends HibernateDao<ProjectFile, Integer> implements ProjectFileDao {
    
    public HibernateProjectFileDao() {
        super(ProjectFile.class);
    }
}