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
import java.io.FilenameFilter;
import org.apache.log4j.Logger;

/**
 * Upload files from Helpdesk Server S to remote Computer A
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class UploadFilesCommand extends AbstractCommand {
    
    private static final Logger logger = Logger.getLogger(UploadFilesCommand.class);
    
    private File remoteDirectory;
    private File temporaryDirectory;
    
    public UploadFilesCommand(Channel channel, File remoteDirectory, File tmpDirectory) {
        super(channel);
        this.remoteDirectory = remoteDirectory;
        this.temporaryDirectory = tmpDirectory;
    }
    

    @Override
    public void execute() throws JSchException{        
        
        ChannelSftp sftpChannel = (ChannelSftp)channel;
        String destinationDirectory = remoteDirectory.getAbsolutePath();
        String sourceDirectory = temporaryDirectory.getAbsolutePath();
        
        //pobiera nazwy plikow z tymczasowego katalogu
        File [] files = getFiles(temporaryDirectory);
        
        
        try {               
            for (File file : files) {
                String destination = remoteDirectory.getAbsolutePath() + "/" + file.getName();
                logger.debug("uploading files: " + file.getAbsolutePath() + " - " + destination);                
                sftpChannel.put(file.getAbsolutePath(), destination);
            }
            
        } catch (SftpException ex) {
            throw new JSchException("Can't upload file from: " + destinationDirectory + " to: " + sourceDirectory, ex);
        }
    }    
    
    private static File[] getFiles(File directory) {
        File [] files = directory.listFiles(new FilenameFilter() {

            //nie uwzgledniamy folderow
            @Override
            public boolean accept(File dir, String name) {
                return name.contains(".");
            }
        });
        
        return files;
    }    
}
