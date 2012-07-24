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
public class Person3 extends Programmer {

    public Person3() {
        super("Steve", "Balmer", "Intermediate",
                "Visual Basic", "VB.NET", "C#", "Visual C++",
                "Assembler");
    }
}
