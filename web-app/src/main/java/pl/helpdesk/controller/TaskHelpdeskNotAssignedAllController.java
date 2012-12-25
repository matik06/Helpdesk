/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import pl.helpdesk.model.Customer;
import pl.helpdesk.model.CustomerUser;
import pl.helpdesk.model.HelpdeskUser;
import pl.helpdesk.service.CustomerUserService;

/**
 *
 * @author matik06
 */
@Controller
@Scope(value = "view")
public class TaskHelpdeskNotAssignedAllController extends HelpdeskTaskController implements Serializable {
    
    @Autowired
    ConstantController constantControler;
    @Autowired
    CustomerUserService customerUserService;
    
    @Override
    protected void reloadList() {
        this.entityList = taskService.findNotAssigned();
    }
    
    private List<CustomerUser> customerUsers = new ArrayList<>();
    
    public String assignMyself() {
        HelpdeskUser user = getLoggedHelpdeskUser();
        this.entity.setResponsible(user);
        updateEntity();
        
        return "pretty:tasks";
    }
    
    public List<SelectItem>getAllCustomerUsers() {        
        System.out.println("calling getAllCustomerUsers()");
        return constantControler.prepareList(customerUsers, "login");
    }
    
    public void testttt() {
        Customer customer = this.entity.getCustomer();    
        System.out.println("customer" + customer.getName());
        
        this.customerUsers = customerUserService.getCustomerUsers(customer);
        System.out.println("customer users: " + customerUsers.size());
    }
    
    
    
}
