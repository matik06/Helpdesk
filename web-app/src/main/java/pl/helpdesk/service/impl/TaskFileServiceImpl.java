/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.TaskFileDao;
import pl.helpdesk.model.TaskFile;
import pl.helpdesk.service.TaskFileService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS)
public class TaskFileServiceImpl extends GenericServiceImpl<TaskFile, Integer, TaskFileDao> implements TaskFileService {
    @Autowired 
    TaskFileDao taskFileDao;

    @Override
    public TaskFileDao getDao() {
        return this.taskFileDao;
    }
}
