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
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Create empty directory on remote machine
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class CreateDirectoryRemotlyCommand extends AbstractCommand {
    
    private static final Logger logger = Logger.getLogger(CreateDirectoryRemotlyCommand.class);
    
    private File remoteDirectory;
    
    public CreateDirectoryRemotlyCommand(Channel channel, File remoteDirectory) {
        super(channel);
        this.remoteDirectory = remoteDirectory;
    }
    

    @Override
    public void execute() throws JSchException{        
        
        ChannelSftp sftpChannel = (ChannelSftp)channel;
        String source = remoteDirectory.getAbsolutePath();
        
        try {
            List<File> parentDirectories = getParentFolders(remoteDirectory);
            
            //stworzenie wczesniejszych katalogow jesli nie istnialy
            
                for (int i = parentDirectories.size() - 1; i >= 0; i--) {
                    File f = parentDirectories.get(i);
                    try {
                        sftpChannel.mkdir(f.getAbsolutePath());
                    } catch (SftpException e) {
                    }
                }                        
            
            sftpChannel.mkdir(remoteDirectory.getAbsolutePath());
        } catch (SftpException ex) {
            throw new JSchException("Can't create directory : " + source, ex);
        }
    }    
    
    private static List<File> getParentFolders(File directory) {
        List<File> files = new ArrayList<File>();
        File current = new File(directory.getAbsolutePath());

        while (current.getParent() != null) {
            String parrentDirectoryPath = current.getParent();
            files.add(new File(parrentDirectoryPath));
            current = new File(parrentDirectoryPath);
        }
        
        return files;
    }          
}
