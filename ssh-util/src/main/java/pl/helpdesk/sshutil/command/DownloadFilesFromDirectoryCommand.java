/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.sshutil.command;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import java.io.File;
import java.util.Vector;
import org.apache.log4j.Logger;

/**
 * Delete all files on remote Computer A from directory
 * directories are not deleted
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class DownloadFilesFromDirectoryCommand extends AbstractCommand {
    
    private static final Logger logger = Logger.getLogger(DownloadFilesFromDirectoryCommand.class);
    
    private File remoteDirectory;
    private File temporaryDirectory;
    
    public DownloadFilesFromDirectoryCommand(Channel channel, File remoteFile, File temporaryDirectory) {
        super(channel);
        this.remoteDirectory = remoteFile;
        this.temporaryDirectory = temporaryDirectory;
    }
    

    @Override
    public void execute() throws JSchException{        
        
        ChannelSftp sftpChannel = (ChannelSftp)channel;
        String remoteDirectoryPath = remoteDirectory.getAbsolutePath();     
        String temporaryDirectoryPath = temporaryDirectory.getAbsolutePath();
        
        try {
            sftpChannel.cd(remoteDirectoryPath);            
            Vector<ChannelSftp.LsEntry> list = sftpChannel.ls("*.*");
            
            for (ChannelSftp.LsEntry file : list) {
                String fileName = file.getFilename();                
                String sourceFile = remoteDirectoryPath + "/" + fileName;  
                String destinationFile = temporaryDirectoryPath + "/" + fileName;
                
                logger.debug("downloading file from: " + sourceFile + " to: " + destinationFile);
                sftpChannel.get(sourceFile, destinationFile);
            }
            
        } catch (SftpException ex) {
            throw new JSchException("Can't delete files from: " + remoteDirectoryPath, ex);
        }
    }    
}
