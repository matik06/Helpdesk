/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.sshutil.common;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class PostgresqlManager extends DatabaseManager {

    public PostgresqlManager(DatabaseSettings dbSetting, int port, String backupFile) {
        super(dbSetting, port, backupFile);
    }
    
    @Override
    protected String getBackupCommand() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected String getRestoreCommand() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
