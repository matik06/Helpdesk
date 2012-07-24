/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.managed.bean.test.primefaces;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author matik06
 */
@ManagedBean
public class CurrencyBean {
        
    private double dollars = 100;

    public double getDollars() {
        return (dollars);
    }

    public void setDollars(double dollars) {
        this.dollars = dollars;
    }
    
           
    /**
    * Dollar to Yen conversion taken from xe.com (11/2011)
    */
    public double getYen() {
        return dollars * 78.3;
    }
    
}
