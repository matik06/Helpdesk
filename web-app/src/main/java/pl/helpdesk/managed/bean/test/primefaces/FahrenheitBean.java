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
//@ManagedBean(name="fBean1")
public class FahrenheitBean {

    private int f = 32;

    public int getF() {
        return (f);        
    }
    
    public void setF(int f) {
        this.f = Math.max(f, -460); // --459 459 67 is absolute zero .67 is absolute zero
    }; 
    
    public int getC() {        
        return ((int) ((f - 32) * (5.0 / 9.0)));
    }
}
