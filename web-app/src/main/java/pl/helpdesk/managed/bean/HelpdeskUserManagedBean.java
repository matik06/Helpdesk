/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataAccessException;
import pl.helpdesk.constant.RoleEnum;

import pl.helpdesk.model.HelpdeskUser;
import pl.helpdesk.model.Role;
import pl.helpdesk.service.HelpdeskUserService;
import pl.helpdesk.service.impl.HelpdeskUserServiceImpl;

/**
 *
 * @author matik06
 */
@ManagedBean(name="helpdeskUserMB")
@RequestScoped
public class HelpdeskUserManagedBean implements Serializable {    
    
    private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";
    
    @ManagedProperty(value="#{HelpdeskUserService}")
    HelpdeskUserService helpdeskUserService;
    
    List<HelpdeskUser> helpdeskUsers;
    
    private int id;
    private String name;
    private String surname;
    private String login;
//    private static final Role role = RoleEnum.CUSTOMER_MANAGER.getBean();
    
    public HelpdeskUserManagedBean() {
//        role.setId(1);
//        role.setName("Admin");
//        
//        HelpdeskUser user = new HelpdeskUser();
//        user.setEmail("matik06@gmail.com");
//        user.setFirstName("Mateusz");
//        user.setLastName("Lubański");
//        user.setLogin("matik06");
//        user.setPassword("abc");
//        
//        helpdeskUserService.save(user);
    }
    
    /**
     * Add User
     *
     * @return String - Response Message
     */
    public String addUser() {
        try {
            HelpdeskUser helpdeskUser = new HelpdeskUser();
            helpdeskUser.setId(getId());
            helpdeskUser.setFirstName(getName());
            helpdeskUser.setLastName(getSurname());
//            helpdeskUser.setRole(role);
//            getHelpdeskUserService().save(helpdeskUser);
            return SUCCESS;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return ERROR;
    }
    
    /**
     * Reset Fields
     *
     */
    public void reset() {
        this.setId(0);
        this.setName("");
        this.setSurname("");
        this.setLogin("");
    }

    public HelpdeskUserService getHelpdeskUserService() {
        return helpdeskUserService;
    }

    public void setHelpdeskUserService(HelpdeskUserService helpdeskUserService) {
        this.helpdeskUserService = helpdeskUserService;
    }

    public List<HelpdeskUser> getHelpdeskUsers() {
        helpdeskUsers = new ArrayList<>();
//        for (int i = 0; i < 2; i++) {
//            HelpdeskUser user = new HelpdeskUser();
//            user.setFirstName(new Integer(i).toString());
//            user.setLastName(new Integer(i).toString());
//            user.setLogin(new Integer(i).toString());
//            user.setId(i);
//            
//            helpdeskUsers.add(user);
//        }
        
        helpdeskUsers.addAll(helpdeskUserService.findAll());                
        return helpdeskUsers;
    }

    public void setHelpdeskUsers(List<HelpdeskUser> helpdeskUsers) {
        this.helpdeskUsers = helpdeskUsers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
