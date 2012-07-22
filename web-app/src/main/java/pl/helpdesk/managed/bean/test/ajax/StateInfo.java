/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.managed.bean.test.ajax;

import javax.faces.model.SelectItem;

/**
 *
 * @author matik06
 */
public class StateInfo {

    private String stateName;
    private SelectItem[] cities;

    public StateInfo(String stateName, SelectItem... cities) {
        this.stateName = stateName;
        this.cities = cities;
    }

    public String getStateName() {
        return (stateName);
    }

    public SelectItem[] getCities() {
        return (cities);
    
    }
    
    private static StateInfo[] nearbyStates = {
        new StateInfo("Maryland",
        new SelectItem("<i>unknown</i>", "--- Choose City ---"),
        new SelectItem("635815", "Baltimore"),
        new SelectItem("57907", "Frederick"),
        new SelectItem("57698", "Gaithersburg"),
        new SelectItem("57402", "Rockville"))    
        //...
        // other states
    };
    
    public static StateInfo[] getNearbyStates() {
        return (nearbyStates);
    }
}