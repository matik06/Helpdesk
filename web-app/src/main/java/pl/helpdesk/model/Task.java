/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import pl.helpdesk.constant.DisplayConfig;
import pl.helpdesk.constant.StatusEnum;

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
@NamedQueries({
    @NamedQuery(name="customerTasksByStatus", query="SELECT t from Task t WHERE t.customer = :customerId AND t.status IN (:statusId) AND t.upgrade IS null"),
    @NamedQuery(name="upgradeTasks", query="SELECT t from Task t WHERE t.upgrade = :upgradeId")    
})
@Entity
@Table(name="Task")
public class Task extends BaseEntity<Integer> implements Serializable {
    
    private Integer id;
    @NotNull
    private Customer customer;
    private User author;
    //jeśli pracownik helpdesku dodaje zgloszenie to moze wybrac pracownika klienta dla ktorego zgloszenie jest dodawane do systemu
    //w przypadku gdy klient dodaje zgloszenie to zostanie on zapisany w polu author a pole author2 bedzie null
    private User author2;
    private HelpdeskUser responsible;
    private Status status;
    private Upgrade upgrade;
    @NotNull
    private Priority priority;
    
    @NotNull
    private String title;
    private String description;
    private Date date;

    private List<TaskNote> notes;
    private List<TaskFile> files;
    private List<Event> events;
    private boolean isReopened;
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "taskId")
    public Integer getId() {
        return id;
    }

    public void setId(Integer roleId) {
        this.id = roleId;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "customerId")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "authorId")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "customer_authorId")
    public User getAuthor2() {
        return author2;
    }

    public void setAuthor2(User author2) {
        this.author2 = author2;
    }
    

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "responsibleId")
    public HelpdeskUser getResponsible() {
        return responsible;
    }

    public void setResponsible(HelpdeskUser responsible) {
                
        this.responsible = responsible;
    }
    
    

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "statusId")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "upgradeId")
    public Upgrade getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(Upgrade upgrade) {
        this.upgrade = upgrade;
    }

    @OneToMany(mappedBy = "task", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<TaskNote> getNotes() {
        return notes;
    }

    public void setNotes(List<TaskNote> notes) {
        this.notes = notes;
    }

    @OneToMany(mappedBy = "task", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<TaskFile> getFiles() {
        return files;
    }

    public void setFiles(List<TaskFile> files) {
        this.files = files;
    }

    @OneToMany(mappedBy = "task", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Type(type="text")
    public String getDescription() {
        return description;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "priorityId")
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }        
    
    @Transient
    public String getShortDescription() {
        
        if (description == null || description.length() < DisplayConfig.longDataShortLength) {
            return description;
        } else {
            return description.substring(0, DisplayConfig.longDataShortLength);
        }            
        
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(nullable = false, columnDefinition = "boolean default false")
    public boolean isIsReopened() {
        return isReopened;
    }

    public void setIsReopened(boolean isReopened) {
        this.isReopened = isReopened;
    }        
    
    public boolean canEdit(User user) {
        
        if (isClosed()) {
            return false;
        } else {            
            if (author == null || author.getId().intValue() == user.getId().intValue()) {
                return true;
            } else {
                return false;
            }
        }        
    }
    
    @Transient
    public boolean isClosed() {
        if (status.getId() == StatusEnum.CLOSED.getValue()) {
            return true;
        } else {
            return false;
        }
    }
    
    
    public boolean canStart() {
        if (status.getId() == StatusEnum.NOT_STARTED.getValue()) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean canSetForUpgrade() {
        if (status.getId() == StatusEnum.IN_PROGRESS.getValue()) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean canClose() {
        if (canSetForUpgrade() || status.getId() == StatusEnum.READY_FOR_UPGRADE.getValue()) {
            return true;
        } else {
            return false;
        }
    }        
    
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 11). // two randomly chosen prime numbers
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

        Task o = (Task) obj;
        return new EqualsBuilder(). // if deriving: appendSuper(super.equals(obj)).
                append(id, o.getId()).
                isEquals();
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", status=" + status + ", title=" + title + ", description=" + description + ", date=" + date + '}';
    }
    
    
}