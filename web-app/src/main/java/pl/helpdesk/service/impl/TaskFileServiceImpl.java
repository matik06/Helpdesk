/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.helpdesk.dao.TaskFileDao;
import pl.helpdesk.model.TaskFile;
import pl.helpdesk.service.TaskFileService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
public class TaskFileServiceImpl extends GenericServiceImpl<TaskFile, Integer, TaskFileDao> implements TaskFileService {
    @Autowired 
    TaskFileDao taskFileDao;

    @Override
    public TaskFileDao getDao() {
        return this.taskFileDao;
    }
}
