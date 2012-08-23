/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
   @Length(min=3)   
   protected String login;
   protected String password;
   protected String phone;
   protected String mobile;
   
   @Transient
   protected String newPassword;

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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        if (newPassword != null && !newPassword.isEmpty()) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(newPassword);
            setPassword(hashedPassword);
        }
    }
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 1003). // two randomly chosen prime numbers
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
