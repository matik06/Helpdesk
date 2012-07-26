/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.managed.bean.test.pf;

import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author matik06
 */
@Controller
@Scope(value="request")
public class Store {
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    @PostConstruct
    public void postConstruct() {
        System.out.println("postconstruct" + category);        
    }
    
    public void process() {
        System.out.println("process" + category);
    }
    
    public String process2() {
        System.out.println("process2" + category);
        return null;
    }
}
