/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.constant;

import org.springframework.beans.factory.annotation.Autowired;
import pl.helpdesk.model.Status;
import pl.helpdesk.service.StatusService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public enum StatusEnum {
    
    NOT_STARTED(1),
    IN_PROGRESS(2),
    NEED_MORE_INFORMATION(3),
    READY_FOR_UPGRADE(4),
    CLOSED(5),
    REOPENED(6);
    
    private int value;
    @Autowired
    private StatusService statusService;

    StatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Status getBean(int value) {
        return statusService.findById(value);
    }

    public static StatusEnum fromInt(int value) throws UnsupportedEnumValue {

        for (StatusEnum status : StatusEnum.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }

        throw new UnsupportedEnumValue("Nieznany status o id: " + value);
    }
}
