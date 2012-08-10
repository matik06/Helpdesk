/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.TaskDao;
import pl.helpdesk.model.Task;
import pl.helpdesk.service.TaskService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS)
public class TaskServiceImpl extends GenericServiceImpl<Task, Integer, TaskDao> implements TaskService {
    @Autowired
    TaskDao taskDao;

    @Override
    public TaskDao getDao() {
        return this.taskDao;
    }
}
