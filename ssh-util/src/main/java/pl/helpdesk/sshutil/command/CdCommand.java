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
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class CdCommand extends AbstractCommand {
    
    private static final Logger logger = Logger.getLogger(DownloadFileCommand.class);
    private File remoteDirectory;
    
    public CdCommand(Channel channel, File remoteDirectory) {
        super(channel);
        this.remoteDirectory = remoteDirectory;
    }
    

    @Override
    public void execute() throws JSchException{        
        
        ChannelSftp sftpChannel = (ChannelSftp)channel;
        String source = remoteDirectory.getAbsolutePath();
        
        try {
            sftpChannel.cd(source);
        } catch (SftpException ex) {
            throw new JSchException("Can't cd to: " + source, ex);
        }
    }
}
