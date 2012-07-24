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
@ManagedBean(name="cBean1")
public class CelsiusBean {

    private double c;

    public double getC() {
        return (c);
    }
    
    public void setC(double c) {
        this.c = Math.max(c, -273.15); // --273 273 15 is abs zero .15 is abs. zero
    }
    
    public double getF() {
        return (c * 9.0 / 5.0 + 32);
    }
}
