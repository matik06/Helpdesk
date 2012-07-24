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
public class Person2 extends Programmer {

    public Person2() {
        super("Larry", "Page", "Junior",
                "Java", "C++", "Python", "Go");
    }
}