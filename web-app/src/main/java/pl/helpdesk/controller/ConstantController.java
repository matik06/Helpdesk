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
import pl.helpdesk.model.HelpdeskUser;
import pl.helpdesk.model.Role;
import pl.helpdesk.service.GenericService;
import pl.helpdesk.service.HelpdeskUserService;
import pl.helpdesk.service.RoleService;
import pl.helpdesk.service.StatusService;

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
    
    private List<SelectItem> helpdeskRoles;
    private List<SelectItem> customerRoles;
    private List<SelectItem> allRoles;
    private List<SelectItem> allTaskStatuses;
    
    @PostConstruct
    public void init() {
        
        allRoles = prepereList(roleService);
        allTaskStatuses = prepereList(statusService);        
        
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
    
    private <T extends BaseEntity> List<SelectItem> prepareList(List<T> entities, String label) {
        List<SelectItem> result = new ArrayList<>();

        //dodanie pustego elementu
        result.add(getEmpty());
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
}
