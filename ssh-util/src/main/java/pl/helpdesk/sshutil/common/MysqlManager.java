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

    public MysqlManager(DatabaseSettings dbSetting) {
        super(dbSetting);
    }
    

    @Override
    protected String getBackupCommand() {
        return "mysqldump" + " -u " + username + "-h 127.0.0.1 -p" + password + " -P " + port.toString() + " " + database + " -r " + "mysql-backup";
    }

    @Override
    protected String getRestoreCommand() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
