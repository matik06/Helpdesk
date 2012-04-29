/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.sshutil.common;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class User {
    
    private String username;
    private String host;
    private int port;
    private String password;
    private Boolean isAuthByKeys;
    private String privateKeyPath;
    private String passphrase;

    public User(String username, String host, int port, String password) {
        this.isAuthByKeys = Boolean.FALSE;
        this.username = username;
        this.host = host;
        this.port = port;
        this.password = password;
    }

    public User(String username, String host, int port) {
        this.isAuthByKeys = Boolean.TRUE;
        this.username = username;
        this.port = port;
        this.host = host;
        
        this.privateKeyPath = SettingEnum.PRIVATE_KEY_PATH.getValue();
    }

    public User(String username, String passphrase, String host, int port) {
        this.isAuthByKeys = Boolean.TRUE;
        this.username = username;
        this.host = host;
        this.port = port;        
        this.passphrase = passphrase;
        this.privateKeyPath = SettingEnum.PRIVATE_KEY_PATH.getValue();
    }
    
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Boolean getIsAuthByKeys() {
        return isAuthByKeys;
    }

    public void setIsAuthByKeys(Boolean isAuthByKeys) {
        this.isAuthByKeys = isAuthByKeys;
    }

    public String getPassphrase() {
        return passphrase;
    }

    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPrivateKeyPath() {
        return privateKeyPath;
    }

    public void setPrivateKeyPath(String privateKeyPath) {
        this.privateKeyPath = privateKeyPath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", host=" + host + ", port=" + port + ", password=" + password + ", isAuthByKeys=" + isAuthByKeys + ", privateKeyPath=" + privateKeyPath + ", passphrase=" + passphrase + '}';
    }        
}
