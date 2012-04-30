/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.sshutil.command.complex;

import com.jcraft.jsch.JSchException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import pl.helpdesk.sshutil.command.Command;
import pl.helpdesk.sshutil.command.DownloadFilesFromDirectoryCommand;
import pl.helpdesk.sshutil.command.UploadFilesCommand;
import pl.helpdesk.sshutil.common.User;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class LogsBackupComplexCommand extends ComplexCommand {
    
    private String remoteDirectory;
    private String logsDirectory;

    /**
     * 
     * @param serverUser - ustawienia polaczenia ssh z serwerem klienta
     * @param operatorUser - ustawienia polaczenia ssh z komputerem operatora
     * @param logsDirectory - folder z logami na serwerze klienta
     * @param remoteDirectory - glowny katalog operatora
     * @throws JSchException 
     */
    public LogsBackupComplexCommand(User serverUser, User operatorUser, String logsDirectory, String remoteDirectory) throws JSchException {
        super(serverUser, operatorUser);
        this.remoteDirectory = remoteDirectory;
        this.logsDirectory = logsDirectory;
    }

    @Override
    protected List<Command> initialize() {
        List<Command> commands = new ArrayList<Command>();

        //pobieranie plikow z logami na serwer Helpdesk
        commands.add(new DownloadFilesFromDirectoryCommand(serverSftpChannel, new File(logsDirectory)));
        
        //usuniecie wszystkich plikow w glownym katalogu na komputerze operatora
        
        //wyslanie pobranych plikow z serwera Hlepdesk na zdalny komputer operatora
        commands.add(new UploadFilesCommand(serverSftpChannel, new File(remoteDirectory), temporaryDirectory));

        return commands;
    }
}
