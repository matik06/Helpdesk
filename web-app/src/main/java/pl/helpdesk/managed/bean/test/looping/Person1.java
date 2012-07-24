/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.managed.bean.test.looping;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author matik06
 */
@ManagedBean
public class Person1 extends Programmer {

    public Person1() {
        super("Larry", "Ellison", "Junior",
                "SQL", "Prolog", "OCL", "Datalog");
    }
}