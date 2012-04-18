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
@Table(name = "HelpdeskPrivilage")
public class HelpdeskPrivilage  implements Serializable {
    
    private Integer id;
    private Server server;
    private Boolean canBackupWebServerLogs;
    private Boolean canBackupTomcatLogs;

    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "helpdeskPrivilageId")
    public Integer getId() {
        return id;
    }

    public void setId(Integer roleId) {
        this.id = roleId;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "serverId")
    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Boolean getCanBackupTomcatLogs() {
        return canBackupTomcatLogs;
    }

    public void setCanBackupTomcatLogs(Boolean canBackupTomcatLogs) {
        this.canBackupTomcatLogs = canBackupTomcatLogs;
    }

    public Boolean getCanBackupWebServerLogs() {
        return canBackupWebServerLogs;
    }

    public void setCanBackupWebServerLogs(Boolean canBackupWebServerLogs) {
        this.canBackupWebServerLogs = canBackupWebServerLogs;
    }
    

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 24). // two randomly chosen prime numbers
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

        HelpdeskPrivilage o = (HelpdeskPrivilage) obj;
        return new EqualsBuilder(). // if deriving: appendSuper(super.equals(obj)).
                append(id, o.getId()).
                isEquals();
    }
}