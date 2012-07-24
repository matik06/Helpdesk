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
public class Person5 extends Programmer {

    public Person5() {
        super("Steve5", "Balmer5", "Intermediate5",
                "Visual Basic5", "VB.NET5", "C#5", "Visual C++5",
                "Assembler5");
    }
}
