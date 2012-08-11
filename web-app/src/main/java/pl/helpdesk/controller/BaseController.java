/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller;

import java.io.Serializable;
import javax.faces.context.FacesContext;

/**
 *
 * @author matik06
 */
public abstract class BaseController implements Serializable {
    
    protected String getRequestParameterAsString(String key) {
        String result = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
        System.out.println("Trying read request parameter " + key + ":" + result);
        return result;
    }
    
    protected Integer getRequestParameterAsInt(String key) {
        String stringValue = getRequestParameterAsString(key);
        return Integer.valueOf(stringValue);
    }
}
