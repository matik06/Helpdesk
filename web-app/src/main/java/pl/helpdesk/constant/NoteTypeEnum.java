/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.constant;

import org.springframework.beans.factory.annotation.Autowired;
import pl.helpdesk.model.NoteType;
import pl.helpdesk.service.NoteTypeService;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public enum NoteTypeEnum {
    
    PUBLIC(1),
    PRIVATE(2),
    UPGRADE_PUBLIC(3),
    UPGRADE_PRIVATE(4);
    
    private int value;
    @Autowired
    private NoteTypeService noteService;

    NoteTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public NoteType getBean(int value) {
        return noteService.findById(value);
    }

    public static NoteTypeEnum fromInt(int value) throws UnsupportedEnumValue {

        for (NoteTypeEnum noteType : NoteTypeEnum.values()) {
            if (noteType.getValue() == value) {
                return noteType;
            }
        }

        throw new UnsupportedEnumValue("Nieznany typ notatki o id: " + value);
    }
}
