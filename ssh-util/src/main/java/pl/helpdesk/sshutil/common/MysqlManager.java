/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.sshutil.common;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class MysqlManager extends DatabaseManager {

    public MysqlManager(String username, String password, String database, int port) {
        super(username, password, database, port);
    }
    

    @Override
    protected String getBackupCommand() {
        return "mysqldump" + " -u " + username + "-h 127.0.0.1 -p" + password + " -P " + port.toString() + " " + database + " -r " + "mysql-backup";
    }
    
}
