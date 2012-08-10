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
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
@Entity
@Table(name="User")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity<Integer> implements Serializable {
    
   protected Integer id;
   
   @NotNull
   protected Role role;   
   protected String firstName;
   protected String lastName;
   @NotNull
   @Email
   protected String email;
   @NotNull
   @Length(min=4, max=10)   
   protected String login;
   @Min(3)
   protected String password;
   protected String phone;
   protected String mobile;
   

    @Id
    @GeneratedValue
    @Column(name = "userId")
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer userId) {
        this.id = userId;
    }

    @OneToOne(fetch = FetchType.EAGER, optional=false)
    @JoinColumn(name = "roleId")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(unique=true)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 2). // two randomly chosen prime numbers
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

        User user = (User) obj;
        return new EqualsBuilder(). // if deriving: appendSuper(super.equals(obj)).
                append(id, user.getId()).
                isEquals();
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", role=" + role + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", login=" + login + ", password=" + password + ", phone=" + phone + ", mobile=" + mobile + '}';
    }
   
}
