/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import pl.helpdesk.constant.RoleEnum;
import pl.helpdesk.converter.ConverterUtil;
import pl.helpdesk.model.BaseEntity;
import pl.helpdesk.model.Customer;
import pl.helpdesk.model.HelpdeskUser;
import pl.helpdesk.model.Role;
import pl.helpdesk.model.User;
import pl.helpdesk.service.*;
import pl.helpdesk.util.SpringSecurityUtil;

/**
 *
 * @author matik06
 */
@Controller
@Scope(value="globalSession")
public class ConstantController implements Serializable {
    
    @Autowired
    RoleService roleService;
    @Autowired
    HelpdeskUserService helpdeskUserService;
    @Autowired
    StatusService statusService;
    @Autowired
    PriorityService priorityService;
    @Autowired
    CustomerService customerService;
    
    
    private List<SelectItem> helpdeskRoles;
    private List<SelectItem> customerRoles;
    private List<SelectItem> allRoles;
    private List<SelectItem> allTaskStatuses;
    private List<SelectItem> allTaskPriorities;
//    private User user;
    
    @PostConstruct
    public void init() {
//        user = SpringSecurityUtil.getLoggedUser();
        
        allRoles = prepereList(roleService);
        allTaskStatuses = prepereList(statusService);              
        allTaskPriorities = prepereList(priorityService);              
        
        List<Role> roles;
        roles = roleService.findAllByIds(RoleEnum.CUSTOMER_MANAGER.getValue(), RoleEnum.CUSTOMER_USER.getValue());
        customerRoles = prepareList(roles);
        
        roles = roleService.findAllByIds(RoleEnum.HELPDESK_MANAGER.getValue(), RoleEnum.HELPDESK_USER.getValue());
        helpdeskRoles = prepareList(roles);
    }        
    
    public List<SelectItem> getHelpdeskUsers() {
        List<HelpdeskUser> users = helpdeskUserService.findAll();
        return prepareList(users, "login");
    }
    
    public List<SelectItem> getAllCustomers() {
        List<Customer> customers = customerService.findAll();
        System.out.println("found " + customers.size() + " customers");
        return prepareList(customers, "name");
    }        
    
    public List<SelectItem> getAllCustomersFroServisant() {
        //TO-DO implementacja getLoggedUser
        HelpdeskUser user = (HelpdeskUser)SpringSecurityUtil.getLoggedUser();
        List<Customer> customers = customerService.findAll();
        return prepareList(customers, "name");
    }
    
    private <T extends BaseEntity> List<SelectItem> prepereList(GenericService<T, Integer> service) {
        List<T> list = service.findAll();
        System.out.println(list);
        return prepareList(list);
    }
    
    private <T extends BaseEntity> List<SelectItem> prepareList(List<T> entities) {
        List<SelectItem> result = new ArrayList<>();
        
        //dodanie pustego elementu
        result.add(getEmpty());
        List<SelectItem> subList = ConverterUtil.convertList(entities);        
        result.addAll(subList);
        
        return result;
    }
    
    public <T extends BaseEntity> List<SelectItem> prepareList(List<T> entities, String label) {
        List<SelectItem> result = new ArrayList<>();

        //dodanie pustego elementu
        result.add(getEmpty());
        List<SelectItem> subList = ConverterUtil.convertList(entities, label);
        result.addAll(subList);

        return result;
    }
    
    public <T extends BaseEntity> List<SelectItem> prepareList(List<T> entities, String label, boolean empty) {
        List<SelectItem> result = new ArrayList<>();

        if (empty) {
            result.add(getEmpty());
        }
        
        List<SelectItem> subList = ConverterUtil.convertList(entities, label);
        result.addAll(subList);

        return result;
    }
    
    private SelectItem getEmpty() {
        return new SelectItem(null, "");
    }
    
    //gettery i settery

    public List<SelectItem> getAllRoles() {
        return allRoles;
    }

    public void setAllRoles(List<SelectItem> allRoles) {
        this.allRoles = allRoles;
    }

    public List<SelectItem> getCustomerRoles() {
        return customerRoles;
    }

    public void setCustomerRoles(List<SelectItem> customerRoles) {
        this.customerRoles = customerRoles;
    }

    public List<SelectItem> getHelpdeskRoles() {
        return helpdeskRoles;
    }

    public void setHelpdeskRoles(List<SelectItem> helpdeskRoles) {
        this.helpdeskRoles = helpdeskRoles;
    }

    public List<SelectItem> getAllTaskStatuses() {
        return allTaskStatuses;
    }

    public void setAllTaskStatuses(List<SelectItem> allTaskStatuses) {
        this.allTaskStatuses = allTaskStatuses;
    }        

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }        

    public List<SelectItem> getAllTaskPriorities() {
        return allTaskPriorities;
    }

    public void setAllTaskPriorities(List<SelectItem> allTaskPriorities) {
        this.allTaskPriorities = allTaskPriorities;
    }
}
