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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Mateusz Lubański <m.lubanskii@gmail.com>
 */
@Entity
@Table(name = "File")
@Inheritance(strategy = InheritanceType.JOINED)
public class File extends BaseEntity<Integer> implements Serializable {

    protected Integer id;
    protected User user;
    protected String name;
    protected String fileSystemName;
    protected String contentType;
    protected Date date;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fileId")
    public Integer getId() {
        return id;
    }

    public void setId(Integer roleId) {
        this.id = roleId;
    }

    @ManyToOne(optional=false, fetch= FetchType.EAGER)
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileSystemName() {
        return fileSystemName;
    }

    public void setFileSystemName(String fileSystemName) {
        this.fileSystemName = fileSystemName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }        

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 3). // two randomly chosen prime numbers
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

        File o = (File) obj;
        return new EqualsBuilder(). // if deriving: appendSuper(super.equals(obj)).
                append(id, o.getId()).
                isEquals();
    }
}