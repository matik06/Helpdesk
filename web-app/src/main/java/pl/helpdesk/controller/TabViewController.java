/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller;

import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.primefaces.event.TabChangeEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author matik06
 */
@Controller
@Scope(value = "view")
public class TabViewController {
    
    private String selectedTab;
    private static final Logger logger = Logger.getLogger(TabViewController.class);
    
    @PostConstruct
    public void init() {
        logger.info("tab view initialization");
    }
    
    public void onTabChange(TabChangeEvent event) {
        logger.info("change tab listener");
        this.selectedTab = event.getTab().getId();
        
        System.out.println(event.getTab());
        System.out.println(event.getTab().getTitle());
        System.out.println(event.getTab().getId());
        System.out.println(event.getTab().getClientId());        
    }
    
    public String getSelectedTab() {
        // selectedTab is a variable that should be set onTabChange()
        return selectedTab;
    }
}
