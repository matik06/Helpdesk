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
import org.apache.log4j.Logger;

/**
 * Download file from Computer A to Helpdesk Server S
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class DownloadFileCommand extends AbstractCommand {
    
    private static final Logger logger = Logger.getLogger(DownloadFileCommand.class);
    
    private File remoteFile;
    private File temporaryDirectory;
    
    public DownloadFileCommand(Channel channel, File remoteFile, File tmpDirectory) {
        super(channel);
        this.remoteFile = remoteFile;
        this.temporaryDirectory = tmpDirectory;
    }
    

    @Override
    public void execute() throws JSchException{        
        
        ChannelSftp sftpChannel = (ChannelSftp)channel;
        String source = remoteFile.getAbsolutePath();
        String destination = temporaryDirectory.getAbsolutePath() + "/" + remoteFile.getName();
        
        try {
            sftpChannel.get(source, destination);
        } catch (SftpException ex) {
            throw new JSchException("Can't download file from: " + source + " to: " + destination, ex);
        }
    }    
}
