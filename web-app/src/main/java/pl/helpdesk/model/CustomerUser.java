/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */

@NamedQueries({
    @NamedQuery(name="customerPMList", query="SELECT cu from CustomerUser cu WHERE cu.customer = :customerId AND cu.role = :roleId and cu.getAllNotifications = true "),
    @NamedQuery(name="customerUsers", query="SELECT cu from CustomerUser cu WHERE cu.customer = :customerId")
})
@Entity
@Table(name="CustomerUser")
public class CustomerUser extends User implements Serializable {
    
    @NotNull
    private Customer customer;    

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerId")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 1021). // two randomly chosen prime numbers
                // if deriving: appendSuper(super.hashCode()).
                append(super.hashCode()).
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

        CustomerUser o = (CustomerUser) obj;
        return new EqualsBuilder(). // if deriving: appendSuper(super.equals(obj)).
                append(id, o.getId()).
                isEquals();
    }
}