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

    public PostgresqlManager(String username, String password, String database, int port) {
        super(username, password, database, port);
    }
    
    @Override
    protected String getBackupCommand() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
