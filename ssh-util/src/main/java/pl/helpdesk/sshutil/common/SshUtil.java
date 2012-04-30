/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.sshutil.common;

import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 * Create and delete temporary transferFolders
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public enum SshUtil {
    
    INSTANCE;
    
    private static final Logger logger = Logger.getLogger(SshUtil.class);
    //temporary folder number
    
    
    public File createTemporaryDirectory() {   
        File file = Files.createTempDir();
        logger.debug("created temporary directory: " + file.getAbsolutePath());
        return file;
    }
    
    public void deleteDirectory(File directory) {
        try {
            FileUtils.deleteDirectory(directory);
        } catch (IOException ex) {
            logger.error("Can't delete directory: " + directory.getAbsolutePath());
        }
    }
    
   public String getCurrentDateAsString() {
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
       return format.format(new Date());
   }
       
       
}
