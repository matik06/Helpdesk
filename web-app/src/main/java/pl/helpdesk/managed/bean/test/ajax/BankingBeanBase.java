/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.managed.bean.test.ajax;

import pl.helpdesk.managed.bean.test.mbI.Customer;

/**
 *
 * @author matik06
 */
public abstract class BankingBeanBase {

    protected String customerId, password;
    protected Customer customer;

    public String getCustomerId() {
        return (customerId);
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPassword() {
        return (password);
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
