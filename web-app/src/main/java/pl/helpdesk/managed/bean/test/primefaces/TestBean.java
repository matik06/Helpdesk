/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.managed.bean.test.primefaces;

import java.util.Date;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author matik06
 */
@ManagedBean
public class TestBean {

    private double number;
    private Date date;

    public double getNumber() {
        return (number);
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String doAction() {
        return("/pages/primefaces/show-test-data");
    }
}
