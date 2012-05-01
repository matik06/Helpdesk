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
 * delete files from remote directory
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class DeleteFilesRemotly extends AbstractCommand {
    
    private static final Logger logger = Logger.getLogger(DeleteFilesRemotly.class);
    
    private File remoteDirectory;
    
    public DeleteFilesRemotly(Channel channel, File remoteFile) {
        super(channel);
        this.remoteDirectory = remoteFile;
    }
    

    @Override
    public void execute() throws JSchException{        
        
        ChannelSftp sftpChannel = (ChannelSftp)channel;
        String remoteDirectoryPath = remoteDirectory.getAbsolutePath();     
        
        try {
            sftpChannel.cd(remoteDirectoryPath);            
            Vector<ChannelSftp.LsEntry> list = sftpChannel.ls("*.*");
            
            for (ChannelSftp.LsEntry file : list) {
                String fileName = file.getFilename();                
                String fileToDelete = remoteDirectoryPath + "/" + fileName;                
                logger.debug("deleting file: " + fileToDelete);
                sftpChannel.rm(fileToDelete);
            }
            
        } catch (SftpException ex) {
            throw new JSchException("Can't delete files from: " + remoteDirectoryPath, ex);
        }
    }    
}
