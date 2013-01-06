/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service;

import java.util.List;
import pl.helpdesk.model.Task;
import pl.helpdesk.model.TaskNote;
import pl.helpdesk.model.Upgrade;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public interface TaskNoteService extends GenericService<TaskNote, Integer> {
    List<TaskNote> getNotes(Task task, Integer noteType);
    List<TaskNote> getNotes(Upgrade upgrade, Integer noteType);
}
