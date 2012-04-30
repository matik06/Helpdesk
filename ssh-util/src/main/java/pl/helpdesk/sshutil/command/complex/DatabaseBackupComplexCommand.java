/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.sshutil.command.complex;

import com.jcraft.jsch.JSchException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import pl.helpdesk.sshutil.command.Command;
import pl.helpdesk.sshutil.command.DatabaseBackupCommand;
import pl.helpdesk.sshutil.command.ForwardPortCommand;
import pl.helpdesk.sshutil.common.AvailableProtFinder;
import pl.helpdesk.sshutil.common.DatabaseSettings;
import pl.helpdesk.sshutil.common.SshUtil;
import pl.helpdesk.sshutil.common.User;

/**
 * Make a database backup from customer server SC and send backup file to remote machine A
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class DatabaseBackupComplexCommand extends ComplexCommand {

    private static final Logger logger = Logger.getLogger(DatabaseBackupComplexCommand.class);
    
    //host na którym jest baza danych
    private static final String R_HOST = "127.0.0.1";
    private DatabaseSettings databaseSettings;
    private String remoteDirectory;
    
    /**
     * 
     * @param serverUser server connection settings
     * @param operatorUser operator computer connection settings
     * @throws JSchException if something goes wrong
     */
    public DatabaseBackupComplexCommand(User serverUser, User operatorUser, DatabaseSettings databaseSettings, String remoteDirectory) throws JSchException {
        super(serverUser, operatorUser);
        this.databaseSettings = databaseSettings;
        this.remoteDirectory = remoteDirectory;
    }

    @Override
    protected List<Command> initialize() {
        List<Command> commands = new ArrayList<Command>();
        
        //pobranie wolnego portu
        int lport = AvailableProtFinder.getNextAvailable();
        
        //przekierowanie portu (tunelowanie)
        commands.add(new ForwardPortCommand(
                serverSession, R_HOST, lport, databaseSettings.getPort()));
        
        //backup bazy danych
        String currentDate = SshUtil.INSTANCE.getCurrentDateAsString();
        String backupFile = this.temporaryDirectory.getAbsolutePath() + "/" + currentDate;
        commands.add(new DatabaseBackupCommand(databaseSettings, lport, backupFile));
        
        //upload file with backup to remote computer
//        commands.add(new UploadFiles(serverSftpChannel, new File(remoteDirectory), temporaryDirectory));
        
        return commands;
    }
    
}
