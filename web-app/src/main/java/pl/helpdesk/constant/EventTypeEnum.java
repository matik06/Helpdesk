/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.constant;

import org.springframework.beans.factory.annotation.Autowired;
import pl.helpdesk.model.EventType;
import pl.helpdesk.service.EventTypeService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public enum EventTypeEnum {
    
    CREATED_TASK(1),
    STARTED_WORKING_OVER_TASK(2),
    STOPPED_NEED_MORE_INFORMATION(3),
    FINISHED_READY_FOR_UPGRADE(4),
    CLOSED(5),
    REOPENED(6);
    
    private int value;
    @Autowired
    private EventTypeService eventTypeService;

    EventTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public EventType getBean(int value) {
        return eventTypeService.findById(value);
    }

    public static EventTypeEnum fromInt(int value) throws UnsupportedEnumValue {

        for (EventTypeEnum eventType : EventTypeEnum.values()) {
            if (eventType.getValue() == value) {
                return eventType;
            }
        }

        throw new UnsupportedEnumValue("Nieznany typ zdarzenia o id: " + value);
    }
}
