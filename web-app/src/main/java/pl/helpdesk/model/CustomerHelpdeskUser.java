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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */

@NamedQueries({
    @NamedQuery(name="helpdeskPMList", query="SELECT chu.helpdeskUser from CustomerHelpdeskUser chu WHERE chu.customer = :customerId AND chu.helpdeskUser.role = :roleId AND chu.helpdeskUser.getAllNotifications = true")
})
@Entity
@Table(name="CustomerHelpdeskUser")
public class CustomerHelpdeskUser extends BaseEntity<Integer> implements Serializable {
    
    private Integer id;
    private HelpdeskUser helpdeskUser;
    private Customer customer;

    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "customerHelpdeskUserId")
    public Integer getId() {
        return id;
    }

    public void setId(Integer roleId) {
        this.id = roleId;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "helpdeskUserId")
    public HelpdeskUser getHelpdeskUser() {
        return helpdeskUser;
    }

    public void setHelpdeskUser(HelpdeskUser helpdeskUserId) {
        this.helpdeskUser = helpdeskUserId;
    }

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

        CustomerHelpdeskUser o = (CustomerHelpdeskUser) obj;
        return new EqualsBuilder(). // if deriving: appendSuper(super.equals(obj)).
                append(id, o.getId()).
                isEquals();
    }
}