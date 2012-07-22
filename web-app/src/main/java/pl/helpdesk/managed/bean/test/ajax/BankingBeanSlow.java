/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.managed.bean.test.ajax;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author matik06
 */
@ManagedBean
public class BankingBeanSlow extends BankingBeanAjax {

    @Override
    public String showBalance() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
        }
        return (super.showBalance());
    }
}
