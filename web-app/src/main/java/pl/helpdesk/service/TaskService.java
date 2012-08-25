/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service;

import java.util.List;
import pl.helpdesk.model.Customer;
import pl.helpdesk.model.Task;
import pl.helpdesk.model.User;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public interface TaskService extends GenericService<Task, Integer> {
    
    //metody uzywane przez pracownikow helpdesk
    public List<Task> findNotAssigned(Customer customer);
    public List<Task> findOpen();
    public List<Task> findOpen(User user);
    public List<Task> findClosed();
    public List<Task> findClosed(User user);
    
    //metody uzywane przez customera    
    public List<Task> findOpen(Customer customer);
    public List<Task> findOpen(Customer customer, User user);
    public List<Task> findClosed(Customer customer);
    public List<Task> findClosed(Customer customer, User user);
            
    public List<Task> findAll(Customer customer);  
}