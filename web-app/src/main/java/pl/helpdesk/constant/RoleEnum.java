/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.constant;

import org.springframework.beans.factory.annotation.Autowired;
import pl.helpdesk.model.Role;
import pl.helpdesk.service.RoleService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public enum RoleEnum {

    ADMIN(1),
    HELPDESK_MANAGER(2),
    HELPDESK_USER(3),
    CUSTOMER_MANAGER(4),
    CUSTOMER_USER(5);

    private int value;
    @Autowired
    private RoleService roleService;

    RoleEnum(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    public Role getBean(int value) {
        return roleService.findById(value);
    }
    
    public static RoleEnum fromInt(int value) throws UnsupportedEnumValue {
        
        for (RoleEnum role : RoleEnum.values()) {
            if (role.getValue() == value) {
                return role;
            }
        }
        
        throw new UnsupportedEnumValue("Nieznana rola o id: " + value);
    }
}
