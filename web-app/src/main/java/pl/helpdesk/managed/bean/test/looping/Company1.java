/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.managed.bean.test.looping;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author matik06
 */
@ManagedBean(eager = true)
public class Company1 {

    private List<Programmer> programmers;

    public Company1() {
        programmers = new ArrayList<Programmer>();
        programmers.add(new Person1());
        programmers.add(new Person2());
        programmers.add(new Person3());
    }

    public List<Programmer> getProgrammers() {
        return (programmers);
    }
}