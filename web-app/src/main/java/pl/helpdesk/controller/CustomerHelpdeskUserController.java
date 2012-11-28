/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import pl.helpdesk.model.Customer;
import pl.helpdesk.model.CustomerHelpdeskUser;
import pl.helpdesk.model.HelpdeskUser;
import pl.helpdesk.service.CustomerHelpdeskUserService;
import pl.helpdesk.service.CustomerService;
import pl.helpdesk.service.GenericService;

/**
 *
 * @author matik06
 */
@Controller
@Scope(value = "view")
public class CustomerHelpdeskUserController extends GridController<CustomerHelpdeskUser> implements Serializable{

    @Autowired
    CustomerHelpdeskUserService customerHelpdeskUserService;
    @Autowired
    CustomerService customerService;
    
    Customer customer;
    @NotNull
    HelpdeskUser helpdeskUser;
    
    public CustomerHelpdeskUserController() {
        super(CustomerHelpdeskUser.class);
    }
    
    @Override
    public GenericService<CustomerHelpdeskUser, Integer> getService() {
        return customerHelpdeskUserService;
    }
    
    @Override
    public void saveOrUpdateEntity() {

        entity.setCustomer(customer);

        if (entity.getId() == null) {
            entity.setHelpdeskUser(helpdeskUser);
            getService().save(entity);            
        } else {
            getService().update(entity);
        }

        reloadList();
    }
    
    @Override
    @PostConstruct
    public void init() {

        Integer customerId = getRequestParameterAsInt("customer");
        customer = customerService.findById(customerId);
        reloadList();
    }

    @Override
    protected void reloadList() {
        Criterion c = Restrictions.eq("customer", customer);
        entityList = customerHelpdeskUserService.findAllByRestriction(c);
    }
    
    //getttery i settery
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public HelpdeskUser getHelpdeskUser() {
        return helpdeskUser;
    }

    public void setHelpdeskUser(HelpdeskUser helpdeskUser) {
        this.helpdeskUser = helpdeskUser;
    }        
}
