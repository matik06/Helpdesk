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
 * Download file from Computer A to Helpdesk Server S
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class DownloadFilesFromDirectoryCommand extends AbstractCommand {
    
    private static final Logger logger = Logger.getLogger(DownloadFilesFromDirectoryCommand.class);
    
    private File remoteDirectory;
    private File temporaryDirectory;
    
    public DownloadFilesFromDirectoryCommand(Channel channel, File remoteFile, File tmpDirectory) {
        super(channel);
        this.remoteDirectory = remoteFile;
        this.temporaryDirectory = tmpDirectory;
    }
    

    @Override
    public void execute() throws JSchException{        
        
        ChannelSftp sftpChannel = (ChannelSftp)channel;
        String sourceDirectory = remoteDirectory.getAbsolutePath();     
        String destination = "";
        
        try {
            sftpChannel.cd(sourceDirectory);            
            Vector<ChannelSftp.LsEntry> list = sftpChannel.ls("*.*");
            
            for (ChannelSftp.LsEntry file : list) {
                String fileName = file.getFilename();
                logger.debug("downloading file: " + fileName);
                String sourceFile = sourceDirectory + "/" + fileName;                
                destination = temporaryDirectory.getAbsolutePath() + "/" + fileName;
                sftpChannel.get(sourceFile, destination);
            }
            
        } catch (SftpException ex) {
            throw new JSchException("Can't download files from: " + sourceDirectory + " to: " + destination, ex);
        }
    }    
}
