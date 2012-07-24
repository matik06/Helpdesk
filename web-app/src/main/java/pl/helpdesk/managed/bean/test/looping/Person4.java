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
public class Person4 extends Programmer {

    public Person4() {
        super("Steve4", "Balmer4", "Intermediate4",
                "Visual Basic4", "VB.NET4", "C#4", "Visual C++4",
                "Assembler4");
    }
}
