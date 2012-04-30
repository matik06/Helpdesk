/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.sshutil.common;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class DatabaseProperties {
    
    public static final DatabaseEnum MYSQL = DatabaseEnum.MYSQL;
    public static final DatabaseEnum POSTGRESQL = DatabaseEnum.POSTGRESQL;
    
    private String username;
    private String password;
    private String database;
    
    private DatabaseEnum databaseType;

    public DatabaseProperties(String username, String password, String database, Integer port, DatabaseEnum databaseType) {
        this.username = username;
        this.password = password;
        this.database = database;
        this.databaseType = databaseType;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public DatabaseEnum getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(DatabaseEnum databaseType) {
        this.databaseType = databaseType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }   
}
