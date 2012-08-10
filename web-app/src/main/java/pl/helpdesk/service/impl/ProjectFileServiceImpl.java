/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.ProjectFileDao;
import pl.helpdesk.model.ProjectFile;
import pl.helpdesk.service.ProjectFileService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS)
public class ProjectFileServiceImpl extends GenericServiceImpl<ProjectFile, Integer, ProjectFileDao> implements ProjectFileService {
    @Autowired 
    ProjectFileDao projectFileDao;

    @Override
    public ProjectFileDao getDao() {
        return this.projectFileDao;
    }
}
