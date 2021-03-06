/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.sshutil.command.complex;

import com.jcraft.jsch.JSchException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import pl.helpdesk.sshutil.command.Command;
import pl.helpdesk.sshutil.command.ForwardPortCommand;
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
//        commands.add(new DownloadFilesFromDirectoryCommand(
//                serverSftpChannel,
//                new File("/home/matik06/test/"),
//                temporaryDirectory));
        
        //wysyłanie plików
//        commands.add(new UploadFiles(
//                serverSftpChannel,
//                new File("/home/matik06/tmp/"),
//                temporaryDirectory));
        
        //tworzenie nowego folderu zdalnie
//        commands.add(new CreateDirectoryRemotlyCommand(
//                serverSftpChannel,
//                new File("/home/matik06/1/2/3/4")
//                ));                
        
        //tworzenie nowego folderu lokalnie
//        commands.add(new CreateDirectoryLocallyCommand(new File("/home/matik/1/2/3/4")));
        
        //usuwanie plikow z podanej lokalizacji
//        commands.add(new DownloadFilesFromDirectoryCommand(serverSftpChannel, new File("/home/matik06/test/")));
        
//        kopiowanie plikow zdalnie
//        commands.add(new CopyFileRemotlyCommand(
//                serverExecChannel,
//                new ArrayList<File>(){{add(new File("/home/matik06/test.txt"));add(new File("/home/matik06/test2.txt"));}},
//                new File("/home/matik06/tmp/3/4/2")));
        
        //kopiowanie plikow lokalnie
//       commands.add(new CopyFilesLocallyCommand(
//               new File("/home/matik/Helpdesk-files/"), new File("/home/matik/Helpdesk-files/upgrades/")));
        
        //backup bazy danych..
        
        commands.add(new ForwardPortCommand(serverSession, "127.0.0.1", 5656, 3306));
        
        return commands;
        
    }
    
    public static void main(String [] args) throws JSchException, IOException {
        User u = new User("matik06", "d3vil0.no-ip.org", 22);
        User u2 = new User("matik06", "d3vil0.no-ip.org", 2222, "matik06");
        ComplexCommand tcc = new TestComplexCommand(u, null);
        
//        DatabaseSettings dbSetting = new DatabaseSettings("root", "matik06", "test", "127.0.0.1", 3306, DatabaseEnum.MYSQL);
//        ComplexCommand tcc = new DatabaseBackupComplexCommand(u, u2, dbSetting, "/home/matik06/main/");
        
//        ComplexCommand tcc = new LogsBackupComplexCommand(u, u2, "/home/matik06/logs/", "/home/matik06/main/");
        
//        List<String> upgradeFiles = new ArrayList<String>();
//        upgradeFiles.add("/home/matik06/projects/B/upgrade2.txt");
//        upgradeFiles.add("/home/matik06/projects/A/upgrade-file1.txt");        
//        ComplexCommand tcc = new UpgradeComplexCommand("/home/matik06/main/", upgradeFiles, "/home/matik06/upgrades", "B2B", u, u2);
        
        tcc.execute();
    }
    
}
