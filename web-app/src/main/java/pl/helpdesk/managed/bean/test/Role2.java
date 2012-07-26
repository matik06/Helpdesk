/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.managed.bean.test;

import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import pl.helpdesk.model.Role;
import pl.helpdesk.service.RoleService;

/**
 *
 * @author matik06
 */
@Controller
@Scope(value="request")
public class Role2 {
    
    private Integer id = 999;
    private String name = "***";
    
    
    @Autowired
    RoleService roleService;
    
    @PostConstruct
    public void init() {
        List<Role> roles = roleService.findAll();
        System.out.println(roles);                
        
        id = roles.get(0).getId();
        name = roles.get(0).getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String process() {
        return null;
    }
}
