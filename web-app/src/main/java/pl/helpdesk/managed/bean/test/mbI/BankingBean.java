/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.managed.bean.test.mbI;

import javax.faces.bean.ManagedBean;



/**
 *
 * @author matik06
 */
@ManagedBean
public class BankingBean {

    private String customerId;
    private String password;

    public String getCustomerId() {
        return (customerId);
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId.trim();
        if (this.customerId.isEmpty()) {
            this.customerId = "(none entered)";
        }
    }

    public String getPassword() {
        return (password);
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    private static CustomerLookupService lookupService =
            new CustomerSimpleMap();

    public String showBalance() {
        if (!password.equals("secret")) {
            return ("/pages/mbI/wrong-password.xhtml");
        }
        customer = lookupService.findCustomer(customerId);
        if (customer == null) {
            return ("/pages/mbI/unknown-customer.xhtml");
        } else if (customer.getBalanceNoSign() < 0) {
            return ("/pages/mbI/negative-balance.xhtml");
        } else if (customer.getBalanceNoSign() < 10000) {
            return ("/pages/mbI/normal-balance.xhtml");
        } else {
            return ("/pages/mbI/high-balance.xhtml");
        }
    }
    
    private Customer customer;

    public Customer getCustomer() {
        return (customer);
    }
}