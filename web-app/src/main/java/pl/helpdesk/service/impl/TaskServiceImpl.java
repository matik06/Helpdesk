/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.helpdesk.constant.StatusEnum;
import pl.helpdesk.dao.TaskDao;
import pl.helpdesk.model.Customer;
import pl.helpdesk.model.Status;
import pl.helpdesk.model.Task;
import pl.helpdesk.model.Upgrade;
import pl.helpdesk.model.User;
import pl.helpdesk.service.StatusService;
import pl.helpdesk.service.TaskService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS)
@Transactional(readOnly=true)
public class TaskServiceImpl extends GenericServiceImpl<Task, Integer, TaskDao> implements TaskService {    
    
    @Autowired
    TaskDao taskDao;
    @Autowired
    StatusService statusService;

    @Override
    public TaskDao getDao() {
        return this.taskDao;
    }

    @Override
    public List<Task> findNotAssigned(Customer customer) {
        Criterion notAssignedRestricion = Restrictions.isNull("responsible");
        Criterion customerRestriction = Restrictions.eq("customer", customer);
        return findAllByRestriction(notAssignedRestricion, customerRestriction);
    }
    
    @Override
    public List<Task> findNotAssigned() {
        Criterion notAssignedRestricion = Restrictions.isNull("responsible");
        return findAllByRestriction(notAssignedRestricion);
    }

    @Override
    public List<Task> findOpen() {        
        Criterion statusRestriction = Restrictions.not(
                Restrictions.in("status.id", new Integer[]{StatusEnum.CLOSED.getValue()}));
        Criterion assignedRestricion = Restrictions.isNotNull("responsible");
        
        return findAllByRestriction(statusRestriction, assignedRestricion);
    }
    
    @Override
    public List<Task> findOpen(User user) {
        Criterion statusRestriction = Restrictions.not(
                Restrictions.in("status.id", new Integer[]{StatusEnum.CLOSED.getValue()}));
        Criterion responsibleCriterion = Restrictions.eq("responsible.id", user.getId());

        return findAllByRestriction(statusRestriction, responsibleCriterion);
    }

    @Override
    public List<Task> findClosed() {
        Criterion statusRestriction = Restrictions.in("status.id", new Integer[]{StatusEnum.CLOSED.getValue()});
        return findAllByRestriction(statusRestriction);
    }
    
    @Override
    public List<Task> findClosed(User user) {

        Criterion statusRestriction = Restrictions.in("status.id", new Integer[]{StatusEnum.CLOSED.getValue()});        
        Criterion responsibleCriterion = Restrictions.eq("responsible.id", user.getId());
        
        return findAllByRestriction(statusRestriction, responsibleCriterion);
    }

    @Override
    //taski należące do customera i których status != zamknięte
    public List<Task> findOpen(Customer customer) {        
        Criterion custRestriction = Restrictions.eq("customer", customer);
        Criterion statusRestriction = Restrictions.not(
                Restrictions.in("status.id", new Integer[]{StatusEnum.CLOSED.getValue()}));                        
        
        return findAllByRestriction(custRestriction, statusRestriction);
    }
    
    @Override
    public List<Task> findOpen(Customer customer, User user) {
        Criterion custRestriction = Restrictions.eq("customer", customer);
        Criterion statusRestriction = Restrictions.not(
                Restrictions.in("status.id", new Integer[]{StatusEnum.CLOSED.getValue()}));
        Criterion authorCriterion = Restrictions.eq("author", user);

        return findAllByRestriction(custRestriction, statusRestriction, authorCriterion);
    }

    @Override
    public List<Task> findClosed(Customer customer) {
        Criterion custRestriction = Restrictions.eq("customer", customer);        
        Criterion statusRestriction = Restrictions.in("status.id", new Integer[]{StatusEnum.CLOSED.getValue()});

        return findAllByRestriction(custRestriction, statusRestriction);
    }
    
    @Override
    public List<Task> findClosed(Customer customer, User user) {
        Criterion custRestriction = Restrictions.eq("customer", customer);
        Criterion statusRestriction = Restrictions.in("status.id", new Integer[]{StatusEnum.CLOSED.getValue()});
        Criterion authorCriterion = Restrictions.eq("author", user);

        return findAllByRestriction(custRestriction, statusRestriction, authorCriterion);
    }

    @Override
    public List<Task> findAll(Customer customer) {        
        Criterion c = Restrictions.eq("customer", customer);
        return super.findAllByRestriction(c);
    }

//    @Override
    @Transactional(readOnly=true)
    public List<Task> findByCustomer(Customer customer, StatusEnum ... statusEnums) {        
        
        List<Status> statuses = new ArrayList<>();
        
        for (StatusEnum statusEnum : statusEnums) {
            statuses.add(statusService.findById(statusEnum.getValue()));
        }
        
        
//        query.setEntity("statusId", status);
        Query query = getSession().getNamedQuery("customerTasksByStatus");
        query.setEntity("customerId", customer);
        query.setParameterList("statusId", statuses);

        return query.list();
    }  

    @Override    
    public List<Task> findByUpgrade(Upgrade upgrade) {
               
        Query query = getSession().getNamedQuery("upgradeTasks");
        query.setEntity("upgradeId", upgrade);               

        return query.list();
    }
    
    
}
