/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.managed.bean.test.ajax;

import javax.faces.bean.ManagedBean;
import pl.helpdesk.managed.bean.test.mbI.CustomerLookupService;
import pl.helpdesk.managed.bean.test.mbI.CustomerSimpleMap;

/**
 *
 * @author matik06
 */
@ManagedBean
public class BankingBeanAjax extends BankingBeanBase {

    private String message = "";

    public String getMessage() {
        return (message);
    }

    public void setMessage(String message) {
        this.message = message;
    
    }
    
    public String showBalance() {
        if (!password.equals("secret")) {
            message = "Incorrect password";
        } else {
            CustomerLookupService service =
                    new CustomerSimpleMap();
            customer = service.findCustomer(customerId);
            if (customer == null) {
                message = "Unknown customer";
            } else {
                message =
                        String.format("Balance for %s %s is $%,.2f",
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getBalanceNoSign());
            }
        }
        return (null);
    }
}