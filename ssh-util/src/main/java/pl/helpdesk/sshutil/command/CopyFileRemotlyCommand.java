/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.sshutil.command;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import java.io.File;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Copy the files on remote Computer A to another location on Computer A
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class CopyFileRemotlyCommand extends AbstractCommand {
    
    private static final Logger logger = Logger.getLogger(CopyFileRemotlyCommand.class);
    
    private List<File> sourceFiles;    
    private File destinationDirectory;   
    
    public CopyFileRemotlyCommand(Channel channel, List<File> sourceFiles, File destinationDirectory) {
        super(channel);
        this.sourceFiles = sourceFiles;
        this.destinationDirectory = destinationDirectory;
    }
    

    @Override
    public void execute() throws JSchException{        
        ChannelExec execChannel = (ChannelExec) channel;
        String cpCommand = "";
        
        for (File sourceFile : sourceFiles) {
            cpCommand += "cp -rf " + sourceFile.getAbsolutePath() + " " + destinationDirectory.getAbsolutePath() + " ; ";            
        }                
        
        logger.debug(cpCommand);
        execChannel.setCommand(cpCommand);
    }    
}
