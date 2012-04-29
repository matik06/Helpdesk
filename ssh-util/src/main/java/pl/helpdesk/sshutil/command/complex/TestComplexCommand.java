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
import pl.helpdesk.sshutil.command.UploadFiles;
import pl.helpdesk.sshutil.common.User;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class TestComplexCommand extends ComplexCommand {
    
    public TestComplexCommand(User server, User operatorComputer) throws JSchException {
        super(server, operatorComputer);
    }

    @Override
    protected List<Command> initialize() {
        List<Command> commands = new ArrayList<Command>();
        
        //pobieranie pliku
//        commands.add(new DownloadFileCommand(
//                serverSftpChannel,
//                new File("/home/matik06/test/1.txt"),
//                temporaryDirectory));
        
//        pobieranie plików
        commands.add(new DownloadFilesFromDirectoryCommand(
                serverSftpChannel,
                new File("/home/matik06/test/"),
                temporaryDirectory));
        
        //wysyłanie plików
        commands.add(new UploadFiles(
                serverSftpChannel,
                new File("/home/matik06/tmp/"),
                temporaryDirectory));
        
        
        
        return commands;
    }
    
    public static void main(String [] args) throws JSchException {
        User u = new User("matik06", "d3vil0.no-ip.org", 22);
        TestComplexCommand tcc = new TestComplexCommand(u, null);
        tcc.execute();
    }
    
}
