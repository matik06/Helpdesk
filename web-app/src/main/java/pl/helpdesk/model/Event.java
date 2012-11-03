/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Entity
@Table(name = "Event")
public class Event extends BaseEntity<Integer> implements Serializable {
    
    private Integer id;
    private EventType type;
    private Task task;
    private User user;
    private Date date;

    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "eventId")
    public Integer getId() {
        return id;
    }

    public void setId(Integer roleId) {
        this.id = roleId;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "eventTypeId")
    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }        

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "taskId")
    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "userId")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 16). // two randomly chosen prime numbers
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

        Event o = (Event) obj;
        return new EqualsBuilder(). // if deriving: appendSuper(super.equals(obj)).
                append(id, o.getId()).
                isEquals();
    }
}