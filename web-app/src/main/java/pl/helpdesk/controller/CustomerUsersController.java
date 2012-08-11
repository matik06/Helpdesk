/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import pl.helpdesk.model.Customer;
import pl.helpdesk.model.CustomerUser;
import pl.helpdesk.service.CustomerService;
import pl.helpdesk.service.CustomerUserService;
import pl.helpdesk.service.GenericService;

/**
 *
 * @author matik06
 */
@Controller
@Scope(value = "view")
public class CustomerUsersController  extends GridController<CustomerUser> implements Serializable {
    
    @Autowired
    CustomerUserService customerUserService;
    @Autowired
    CustomerService customerService;
    
    Customer customer;
    
    public CustomerUsersController() {
        super(CustomerUser.class);
    }

    @Override
    public GenericService<CustomerUser, Integer> getService() {
        return customerUserService;
    }
    
    @Override
    public void saveOrUpdateEntity() {

        System.out.println("save or update");
        entity.setCustomer(customer);

        if (entity.getId() == null) {
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
        entityList = customerUserService.findAllByRestriction(c);
    }
    
    
    //getttery i settery
    
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
