/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Entity
@Table(name="TaskNote")
public class TaskNote  implements Serializable {
    
    private Integer id;
    private Note note;
    private NoteType type;
    private Task task;
    
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "taskNoteId")
    public Integer getId() {
        return id;
    }

    public void setId(Integer roleId) {
        this.id = roleId;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "noteId")
    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }
    
    @ManyToOne(fetch= FetchType.EAGER, optional=false)
    @JoinColumn(name="noteTypeId")
    public NoteType getType() {
        return type;
    }

    public void setType(NoteType type) {
        this.type = type;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "taskId")
    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
    
    
        

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 13). // two randomly chosen prime numbers
                // if deriving: appendSuper(super.hashCode()).
                append(id).
                toHashCode();
    }
    
    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }

        TaskNote o = (TaskNote) obj;
        return new EqualsBuilder(). // if deriving: appendSuper(super.equals(obj)).
                append(id, o.getId()).
                isEquals();
    }

    @Override
    public String toString() {
        return "TaskNote{" + "id=" + id + ", note=" + note + ", type=" + type + ", task=" + task + '}';
    }
}