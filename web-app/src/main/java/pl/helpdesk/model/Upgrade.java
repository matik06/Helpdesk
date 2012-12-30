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
import javax.validation.constraints.NotNull;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
@NamedQueries({
    @NamedQuery(name = "upgrades", query = "SELECT u from Upgrade u WHERE u.isCompleted = :isCompleted"),
    @NamedQuery(name = "upgradesByCustomer", query = "SELECT u from Upgrade u WHERE u.customer = :customerId AND u.isCompleted = :isCompleted")    
})
@Entity
@Table(name="Upgrade")
public class Upgrade extends BaseEntity<Integer> implements Serializable {
    
    private Integer id;
    @NotNull
    private Customer customer;
    private User user;
    
    @NotNull
    private String description;    
    private Date date;
    private Boolean isCompleted;
    
//    private List<UpgradeFile> files;
//    private List<UpgradeNote> notes;
    private List<Task> tasks;
    
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "updateId")
    public Integer getId() {
        return id;
    }

    public void setId(Integer roleId) {
        this.id = roleId;
    }

    @ManyToOne(fetch= FetchType.EAGER, optional=false)
    @JoinColumn(name = "customerId")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "userId")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable=false, columnDefinition="boolean default false")
    public Boolean getIsCompleted() {
        return isCompleted;
    }
    
    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }        
    
//    @OneToMany(mappedBy = "upgrade", cascade = {CascadeType.ALL}, orphanRemoval = true)
//    @LazyCollection(LazyCollectionOption.FALSE)
//    public List<UpgradeFile> getFiles() {
//        return files;
//    }
//
//    public void setFiles(List<UpgradeFile> files) {
//        this.files = files;
//    }
//
//    @OneToMany(mappedBy = "upgrade", cascade = {CascadeType.ALL}, orphanRemoval = true)
//    @LazyCollection(LazyCollectionOption.FALSE)
//    public List<UpgradeNote> getNotes() {
//        return notes;
//    }
//
//    public void setNotes(List<UpgradeNote> notes) {
//        this.notes = notes;
//    }

    @OneToMany(mappedBy = "upgrade", fetch= FetchType.LAZY, orphanRemoval=true, cascade= CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)    
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 1007). // two randomly chosen prime numbers
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

        Upgrade o = (Upgrade) obj;
        return new EqualsBuilder(). // if deriving: appendSuper(super.equals(obj)).
                append(id, o.getId()).
                isEquals();
    }

    @Override
    public String toString() {
        return "Upgrade{" + "id=" + id + ", customer=" + customer + ", user=" + user + ", description=" + description + ", date=" + date + ", tasks=" + tasks + '}';
    }   
}