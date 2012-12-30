/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import pl.helpdesk.model.Customer;
import pl.helpdesk.model.CustomerUser;
import pl.helpdesk.model.HelpdeskUser;
import pl.helpdesk.model.Upgrade;
import pl.helpdesk.service.GenericService;
import pl.helpdesk.service.UpgradeService;

/**
 *
 * @author MAT1K
 */
@Controller
@Scope(value = "view")
public class UpdateCompletedController extends GridController<Upgrade> {
    
    @Autowired
    protected UpgradeService upgradeService;
    
    public UpdateCompletedController() {
        super(Upgrade.class);
    }

    @Override
    public GenericService getService() {
        return upgradeService;
    }    

    @Override
    protected void reloadList() {
        if (getLoggedUser() instanceof HelpdeskUser) {
            entityList = upgradeService.findAll(true);
        } else if(getLoggedUser() instanceof CustomerUser) {
            Customer customer = getLoggedCustomerUser().getCustomer();
            entityList = upgradeService.findAll(customer, true);
        }        
    }
}
