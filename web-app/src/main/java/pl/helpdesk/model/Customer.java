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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
@Entity
@Table(name = "Customer")
public class Customer implements Serializable {

    private Integer id;
    private List<CustomerFile> files;
    private List<Task> tasks;
    private List<CustomerPriority> priorities;
    private List<CustomerUser> customerUsers;
    private List<Server> servers;
    
    private String name;
    private Date start;
    private Date end;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerId")
    public Integer getId() {
        return id;
    }

    public void setId(Integer roleId) {
        this.id = roleId;
    }

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<CustomerFile> getFiles() {
        return files;
    }

    public void setFiles(List<CustomerFile> files) {
        this.files = files;
    }

    @OneToMany(mappedBy = "customer", fetch= FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<CustomerPriority> getPriorities() {
        return priorities;
    }

    public void setPriorities(List<CustomerPriority> priorities) {
        this.priorities = priorities;
    }

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch= FetchType.LAZY)
    public List<CustomerUser> getCustomerUsers() {
        return customerUsers;
    }

    public void setCustomerUsers(List<CustomerUser> customerUsers) {
        this.customerUsers = customerUsers;
    }

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }
    
    
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }
    

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 4). // two randomly chosen prime numbers
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

        Customer o = (Customer) obj;
        return new EqualsBuilder(). // if deriving: appendSuper(super.equals(obj)).
                append(id, o.getId()).
                isEquals();
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", files=" + files + ", name=" + name + ", start=" + start + ", end=" + end + '}';
    }
}