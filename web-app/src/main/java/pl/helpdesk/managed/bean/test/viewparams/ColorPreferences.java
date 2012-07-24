/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.managed.bean.test.viewparams;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author matik06
 */
@ManagedBean
@SessionScoped
public class ColorPreferences implements Serializable {

    private String foreground = "black", background = "#fdf5e6";

    public String getForeground() {
        return (foreground);
    }

    public void setForeground(String foreground) {
        if (!isEmpty(foreground)) {
            this.foreground = foreground;
        }
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        if (!isEmpty(background)) {
            this.background = background;
        }        
    }

    
    
    public String getStyle() {
        String style =
                String.format("color: %s; background-color: %s",
                foreground, background);
        return (style);
    }
    
    private boolean isEmpty(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
