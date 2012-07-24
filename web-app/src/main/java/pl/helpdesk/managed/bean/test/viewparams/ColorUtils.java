/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.managed.bean.test.viewparams;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.apache.commons.lang.math.RandomUtils;

/**
 *
 * @author matik06
 */
@ManagedBean
@ApplicationScoped
public class ColorUtils {

    private String[] foregrounds = {
        "DarkBlue", "DarkCyan", "DarkGoldenRod", "DarkGray"};
private String[] backgrounds = {
        "LightBlue", "LightCyan", "LightGoldenRodYellow"};
public String getRandomForeground() {
        return ((foregrounds[0]));
    }

    public String getRandomBackground() {
        return ((backgrounds)[2]);
    }
}