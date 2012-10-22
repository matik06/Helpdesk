/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.util;

import java.util.LinkedList;
import java.util.List;
import pl.helpdesk.constant.NoteTypeEnum;
import pl.helpdesk.model.Note;
import pl.helpdesk.model.TaskNote;

/**
 *
 * @author matik06
 */
public class NoteFilter {
    
    private List<TaskNote> notes;
    
    public NoteFilter(List<TaskNote> notes) {
        this.notes = notes;
    }
    
    public List<TaskNote> getNotes(NoteTypeEnum type) {
        List<TaskNote> result = new LinkedList<>();
        
        for (TaskNote note : notes) {
            if (note.getType().getId().equals(type.getValue())) {
                result.add(note);
            }
        }
        
        return result;
    }
}
