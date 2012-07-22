/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.managed.bean.test.eventhandling;

import java.io.Serializable;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author matik06
 */
@ManagedBean
@SessionScoped
public class FormSettings implements Serializable {

    private boolean isNormalSize = true;
    
    
    private boolean isEnglish = true;
    private final Locale ENGLISH = Locale.ENGLISH;
    private final Locale SPANISH = new Locale("es");
    private boolean checked;

    public String getBodyStyleClass() {
        if (isNormalSize) {
            return ("medium");
        } else {
            return ("large");
        }
    }

    public void setNormalSize(ActionEvent event) {
        isNormalSize = true;
    }

    public void setLargeSize(ActionEvent event) {
        isNormalSize = false;
    }
    
    
    public Locale getLocale() {
        if (isEnglish) {
            return (ENGLISH);
        } else {
            return (SPANISH);
        }
    }
    
    public void swapLocale1(ActionEvent event) {
        switchLocale();
    }
    
    public void swapLocale2(ValueChangeEvent event) {
        
        System.out.println("is english: " + isEnglish);
        
        Boolean flag = (Boolean) event.getNewValue();
        if (flag) {
            switchLocale();
        }
        
        System.out.println("is English: " + isEnglish);
        
        System.out.println("old value: " + event.getOldValue());
        System.out.println("new value: " + event.getNewValue());
    }

    private void switchLocale() {
        isEnglish = !isEnglish;
        Locale newLocale;
        if (isEnglish) {
            newLocale = ENGLISH;
        } else {
            newLocale = SPANISH;
        }
                
        FacesContext.getCurrentInstance().getViewRoot().setLocale(newLocale);
    }

    public boolean isChecked() {
        return false;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setIsEnglish(boolean isEnglish) {
        this.isEnglish = isEnglish;
    }

    public void setIsNormalSize(boolean isNormalSize) {
        this.isNormalSize = isNormalSize;
    }        
}