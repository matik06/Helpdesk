/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.constant;

import org.springframework.beans.factory.annotation.Autowired;
import pl.helpdesk.model.Priority;
import pl.helpdesk.service.PriorityService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public enum PriorityEnum {
    
    LOW(1),
    NORMAL(2),
    HIGH(3),
    CRITICAL(4);
    
    private int value;
    @Autowired
    private PriorityService priorityService;

    PriorityEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Priority getBean(int value) {
        return priorityService.findById(value);
    }

    public static PriorityEnum fromInt(int value) throws UnsupportedEnumValue {

        for (PriorityEnum priority : PriorityEnum.values()) {
            if (priority.getValue() == value) {
                return priority;
            }
        }

        throw new UnsupportedEnumValue("Nieznany priorytet o id: " + value);
    }
}
