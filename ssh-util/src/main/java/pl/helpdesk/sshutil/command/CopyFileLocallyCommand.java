/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.sshutil.command;

import com.jcraft.jsch.JSchException;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.log4j.Logger;

/**
 * Copy the files from directory on local Computer A to another directory on Computer A
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class CopyFileLocallyCommand extends AbstractCommand {
    
    private static final Logger logger = Logger.getLogger(CopyFileLocallyCommand.class);
    
    private File sourceDirectory;    
    private File destinationDirectory;   
    
    public CopyFileLocallyCommand(File sourceDirectory, File destinationDirectory) {
        super(null);
        this.sourceDirectory = sourceDirectory;
        this.destinationDirectory = destinationDirectory;
    }
    

    @Override
    public void execute() throws JSchException, IOException{        
        
        File [] files = getFiles(sourceDirectory);
        
        for (File file : files) {
            String destinationFile = destinationDirectory.getAbsolutePath() + "/" + file.getName();
            logger.debug("cp " + file.getAbsolutePath() + " " + destinationFile);
            FileUtils.copyFile(file, new File(destinationFile));
        }
    }    
    
    private File[] getFiles(File directory) {
        
        return directory.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                if (name.contains(".")) {
                    return true;
                } else {
                    return false;
                }
         
            }
        }); 
    }
}
