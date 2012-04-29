/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.sshutil.common;

import com.google.common.io.Files;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import javax.naming.spi.DirectoryManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

/**
 * Create and delete temporary transferFolders
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public enum FolderManager {
    
    INSTANCE;
    
    private static final Logger logger = Logger.getLogger(FolderManager.class);
    //temporary folder number
    
    public void createDirectory(File dir) throws IOException {
        boolean success = dir.mkdir();
        if (!success) {
            throw new IOException("Can't create empty directory: " + dir.getAbsolutePath());
        }
    }
    
    public File createTemporaryDirectory() {   
        File file = Files.createTempDir();
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getName());
        return file;
    }
    
    public void deleteDirectory(File directory) {
        try {
            FileUtils.deleteDirectory(directory);
        } catch (IOException ex) {
            logger.error("Can't delete directory: " + directory.getAbsolutePath());
        }
    }
    
    public void move(File from, File to) throws IOException {
        
        FileChannel in = new FileInputStream(from).getChannel();
        FileChannel out = new FileOutputStream(to).getChannel();
                
        out.transferFrom(in, 0, in.size());
        in.close();
        out.close();
        
        
    }
}
