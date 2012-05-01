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
import pl.helpdesk.sshutil.common.DatabaseSettings;
import pl.helpdesk.sshutil.common.MysqlManager;
import pl.helpdesk.sshutil.common.PostgresqlManager;

/**
 * make a backup of the mysql database
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class DatabaseBackupCommand extends AbstractCommand {
    
    private static final Logger logger = Logger.getLogger(DatabaseBackupCommand.class);
    
    private DatabaseSettings dbSetting; 
    private int lport;
    private String file;
    
    public DatabaseBackupCommand(DatabaseSettings databaseProperties, int lport, String file) {
        super(null);
        this.dbSetting = databaseProperties;
        this.lport = lport;
        this.file = file;
    }
    

    @Override
    public void execute() throws JSchException, IOException {  
        
        DatabaseManager databaseManager = null;
        
        //utworzenie odpowiedniego typu managera
        if (dbSetting.getDatabaseType() == DatabaseEnum.MYSQL) {
            databaseManager = new MysqlManager(dbSetting, lport, file);
        } else if(dbSetting.getDatabaseType() == DatabaseEnum.POSTGRESQL) {
            databaseManager = new PostgresqlManager(dbSetting, lport, file);
        } else {
            throw new UnsupportedDataTypeException("Unsuported database manager: " + dbSetting.getDatabaseType());
        }
        
        //backup bazy do pliku
        try {
            boolean success = databaseManager.backup(file);
            if (!success) {
                //backup nieudany...
            }
        } catch (InterruptedException ex) {
            throw new IOException(ex);
        }
    }    
}
