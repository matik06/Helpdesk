/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.sshutil.command;

import com.jcraft.jsch.JSchException;
import java.io.IOException;
import javax.activation.UnsupportedDataTypeException;
import org.apache.log4j.Logger;
import pl.helpdesk.sshutil.common.DatabaseEnum;
import pl.helpdesk.sshutil.common.DatabaseManager;
import pl.helpdesk.sshutil.common.DatabaseProperties;
import pl.helpdesk.sshutil.common.MysqlManager;
import pl.helpdesk.sshutil.common.PostgresqlManager;

/**
 * make a backup of the mysql database
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class DatabaseBackupCommand extends AbstractCommand {
    
    private static final Logger logger = Logger.getLogger(DatabaseBackupCommand.class);
    
    private DatabaseProperties dbSetting; 
    private int port;
    private String file;
    
    public DatabaseBackupCommand(DatabaseProperties databaseProperties, int port, String file) {
        super(null);
        this.dbSetting = databaseProperties;
        this.port = port;
        this.file = file;
    }
    

    @Override
    public void execute() throws JSchException, IOException {  
        
        DatabaseManager databaseManager = null;
        
        //utworzenie odpowiedniego typu managera
        if (dbSetting.getDatabaseType() == DatabaseEnum.MYSQL) {
            databaseManager = new MysqlManager(
                    dbSetting.getUsername(),
                    dbSetting.getPassword(),
                    dbSetting.getDatabase(),
                    port);
        } else if(dbSetting.getDatabaseType() == DatabaseEnum.POSTGRESQL) {
            databaseManager = new PostgresqlManager(
                    dbSetting.getUsername(),
                    dbSetting.getPassword(),
                    dbSetting.getDatabase(),
                    port);
        } else {
            throw new UnsupportedDataTypeException("Unsuported database manager: " + dbSetting.getDatabaseType());
        }
        
        //backup bazy do pliku
        try {
            databaseManager.backup(file);
        } catch (InterruptedException ex) {
            throw new IOException(ex);
        }
    }    
}
