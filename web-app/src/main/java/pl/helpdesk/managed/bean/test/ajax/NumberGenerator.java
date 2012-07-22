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
public class NumberGenerator {

    private double number = Math.random();
    private double range = 1.0;

    public double getRange() {
        return (range);
    }

    public void setRange(double range) {
        this.range = range;
    }

    public double getNumber() {
        System.out.println("returning number: " + number);
        return (number);
    }

    public String randomize() {
        number = range * Math.random();
        return (null);  // In non-Ajax apps, means to redisplay form
    }
}