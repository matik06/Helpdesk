/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.managed.bean.test.ajax;

import java.util.Random;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.apache.commons.lang.math.RandomUtils;

/**
 *
 * @author matik06
 */
@ManagedBean
@ApplicationScoped
public class TextGenerator {

    private String[] colors = {"red", "yellow", "blue", "green"};
    private String[] phrases = {"Blah, blah, blah. ",
        "Yadda, yadda, yadda. ",
        "Foo, bar, baz. "
    };
    
    public String getRandomColor() {
        return colors[new Random().nextInt(colors.length)];
    }

    public String getRandomText() {
        int numPhrases = 1 + new Random().nextInt(10);
        String text = "";
        for (int i = 0; i < numPhrases; i++) {
            text += phrases[new Random().nextInt(phrases.length)];
        }
        return (text);
    }
    
    public String getStartCell() {
        String text =
                String.format("<th bgcolor='%s'>",
                getRandomColor());
        return (text);
    }

    public String getEndCell() {
        return ("</th>");
    }
}
