/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Entity
@Table(name="CustomerPriority")
public class CustomerPriority extends BaseEntity<Integer> implements Serializable {
    
    private Integer id;
    private Priority priority;
    private Customer customer;
    
    private String name;
    private Integer executionDuration;

    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "customerPriorityId")
    public Integer getId() {
        return id;
    }

    public void setId(Integer roleId) {
        this.id = roleId;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "priorityId")
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerId")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getExecutionDuration() {
        return executionDuration;
    }

    public void setExecutionDuration(Integer executionDuration) {
        this.executionDuration = executionDuration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 19). // two randomly chosen prime numbers
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

        CustomerPriority o = (CustomerPriority) obj;
        return new EqualsBuilder(). // if deriving: appendSuper(super.equals(obj)).
                append(id, o.getId()).
                isEquals();
    }

    @Override
    public String toString() {
        return "CustomerPriority{" + "id=" + id + ", priority=" + priority + ", customer=" + customer + ", name=" + name + ", executionDuration=" + executionDuration + '}';
    }
}