/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import org.springframework.stereotype.Service;
import pl.helpdesk.dao.TaskDao;
import pl.helpdesk.model.Task;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
public class TaskServiceImpl extends GenericServiceImpl<Task, Integer, TaskDao> {
    
}
