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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Entity
@Table(name = "Server")
public class Server  implements Serializable {
    
    private Integer id;
    private Customer customer;
    private HelpdeskPrivilage helpdeskPrivilage;
    
    private String ip;
    private String systemLogin;
    private String systemPassword;
    private String webServerLotin;
    private String webServerPassword;
    private String databaseLogin;
    private String databasePassword;
    private String databaseUrl;

    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "serverId")
    public Integer getId() {
        return id;
    }

    public void setId(Integer roleId) {
        this.id = roleId;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerId")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @OneToOne(mappedBy = "server", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public HelpdeskPrivilage getHelpdeskPrivilage() {
        return helpdeskPrivilage;
    }

    public void setHelpdeskPrivilage(HelpdeskPrivilage helpdeskPrivilage) {
        this.helpdeskPrivilage = helpdeskPrivilage;
    }

    public String getDatabaseLogin() {
        return databaseLogin;
    }

    public void setDatabaseLogin(String databaseLogin) {
        this.databaseLogin = databaseLogin;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSystemLogin() {
        return systemLogin;
    }

    public void setSystemLogin(String systemLogin) {
        this.systemLogin = systemLogin;
    }

    public String getSystemPassword() {
        return systemPassword;
    }

    public void setSystemPassword(String systemPassword) {
        this.systemPassword = systemPassword;
    }

    public String getWebServerLotin() {
        return webServerLotin;
    }

    public void setWebServerLotin(String webServerLotin) {
        this.webServerLotin = webServerLotin;
    }

    public String getWebServerPassword() {
        return webServerPassword;
    }

    public void setWebServerPassword(String webServerPassword) {
        this.webServerPassword = webServerPassword;
    }
    

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 23). // two randomly chosen prime numbers
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

        Server o = (Server) obj;
        return new EqualsBuilder(). // if deriving: appendSuper(super.equals(obj)).
                append(id, o.getId()).
                isEquals();
    }

    @Override
    public String toString() {
        return "Server{" + "id=" + id + ", customer=" + customer + ", ip=" + ip + ", systemLogin=" + systemLogin + ", systemPassword=" + systemPassword + ", webServerLotin=" + webServerLotin + ", webServerPassword=" + webServerPassword + ", databaseLogin=" + databaseLogin + ", databasePassword=" + databasePassword + ", databaseUrl=" + databaseUrl + '}';
    }
}