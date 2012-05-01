/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.sshutil.command.complex;

import com.jcraft.jsch.JSchException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.helpdesk.sshutil.command.Command;
import pl.helpdesk.sshutil.command.CopyFilesLocallyCommand;
import pl.helpdesk.sshutil.command.CopyFilesRemotlyCommand;
import pl.helpdesk.sshutil.command.CreateDirectoryLocallyCommand;
import pl.helpdesk.sshutil.command.CreateDirectoryRemotlyCommand;
import pl.helpdesk.sshutil.command.DeleteFilesRemotly;
import pl.helpdesk.sshutil.command.DownloadFilesFromDirectoryCommand;
import pl.helpdesk.sshutil.command.UploadFilesCommand;
import pl.helpdesk.sshutil.common.SettingEnum;
import pl.helpdesk.sshutil.common.SshUtil;
import pl.helpdesk.sshutil.common.User;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class UpgradeComplexCommand extends ComplexCommand {
    
    private String customerName;
    private String remoteDirectoryPath;
    private List<String> remoteUpgradeFilesPath;
    private String customerServerUpgradePath;

    public UpgradeComplexCommand(String remoteDirectoryPath, List<String> remoteUpgradeFilesPath, String customerServerUpgradePath, String customerName, User serverUser, User operatorUser) throws JSchException {
        super(serverUser, operatorUser);
        
        this.remoteDirectoryPath = remoteDirectoryPath;
        this.remoteUpgradeFilesPath = remoteUpgradeFilesPath;
        this.customerServerUpgradePath = customerServerUpgradePath;
        this.customerName = customerName;
    }

    

    @Override
    protected List<Command> initialize() {
        List<Command> commands = new ArrayList<Command>();

        //usuniecie wszystkich plikow w glownym katalogu na komputerze operatora
        commands.add(new DeleteFilesRemotly(operatorSftpChannel, new File(remoteDirectoryPath)));
        
        //skopiowanie wszystkich wymaganych plikow do glownego katalogu
        commands.add(new CopyFilesRemotlyCommand(operatorExecChannel, remoteUpgradeFilesPath, new File(remoteDirectoryPath)));
        
        //pobranie plikow na serwer helpdesk
        commands.add(new DownloadFilesFromDirectoryCommand(operatorSftpChannel, new File(remoteDirectoryPath), temporaryDirectory));
        
        String currentDate = SshUtil.INSTANCE.getCurrentDateAsString();
        
        //utowrzenie katalogu lokalnie
        String upgradesBackupPath = SettingEnum.UPGRADES_BACKUP_PATH.getValue() + "/" + customerName + "/" + currentDate + "/";
        commands.add(new CreateDirectoryLocallyCommand(new File(upgradesBackupPath)));
                
        //zrobienie kopii plikow
        commands.add(new CopyFilesLocallyCommand(temporaryDirectory, new File(upgradesBackupPath)));
        
        //create empty folder with current date on customer server
        this.customerServerUpgradePath += "/" + currentDate + "/";
        commands.add(new CreateDirectoryRemotlyCommand(serverSftpChannel, new File(customerServerUpgradePath)));        
        
        //send upgrade files from helpdesk server to customer server
        commands.add(new UploadFilesCommand(serverSftpChannel, new File(customerServerUpgradePath), temporaryDirectory));

        return commands;
    }
    
}
