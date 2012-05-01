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
    
    public MysqlManager(DatabaseSettings dbSetting, int lport, String backupFile) {
        super(dbSetting, lport, backupFile);
    }
    

    @Override
    protected String getBackupCommand() {
        return "mysqldump" + " -u " + username + " -h " + host + " -p" + password + " -P " + lport + " " + database + " -r " + backupFile;
    }

    @Override
    protected String getRestoreCommand() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
