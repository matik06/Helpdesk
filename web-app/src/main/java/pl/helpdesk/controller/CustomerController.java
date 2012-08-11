/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller;

import java.io.Serializable;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import pl.helpdesk.model.Customer;
import pl.helpdesk.service.CustomerService;
import pl.helpdesk.service.GenericService;

/**
 *
 * @author matik06
 */
@Controller
@Scope(value = "view")
public class CustomerController extends GridController<Customer> implements Serializable {
    
    @Autowired
    CustomerService customerService;
    
    
    public CustomerController() {
        super(Customer.class);
    }
    
    private Date currentDate = new Date();

    public Date getCurrentDate() {
        return currentDate;
    }    

    @Override
    public GenericService<Customer, Integer> getService() {
        return customerService;
    }
}
