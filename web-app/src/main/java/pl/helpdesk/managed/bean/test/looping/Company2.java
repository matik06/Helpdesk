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
@ManagedBean(eager = true)
public class Company2 {

    private Programmer[] programmers = {
        new Person1(), new Person2(), new Person3(),
        new Person4(), new Person5()
    };

    public Programmer[] getProgrammers() {
        return (programmers);
    }
}