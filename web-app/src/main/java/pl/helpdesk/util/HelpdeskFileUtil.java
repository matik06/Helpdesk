/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.util;

import java.io.*;
import java.util.UUID;
import java.util.logging.Level;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author matik06
 */
@Service
public class HelpdeskFileUtil {
    
    private static final Logger logger = Logger.getLogger(HelpdeskFileUtil.class);
    
    @Value(value="${upload.path}")
    private String folderpath;
    
    public String upload(InputStream is, String extension) {
        String filename = generateNextFileName(extension);        
        copyFile(folderpath + filename, is);                
        return filename;
    }
    
    public InputStream getFileStream(String filename) {        
        try {
            File file = new File(folderpath + filename);
            return new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            logger.error(ex.getMessage(), ex);
            return null;
        }
    }
    
    public boolean delete(String filename) {
        File f = new File(folderpath + filename);
        return f.delete();
    }
    
    private String generateNextFileName(String extension) {
        
        String generatedFileName = "unknown";
        
        do {
            String randomName = UUID.randomUUID().toString();
            generatedFileName = randomName + "." + extension;
        } while(new File(generatedFileName).exists());
        
        return generatedFileName; 
    }
    
    private void copyFile(String fileName, InputStream in) {
        
        OutputStream os = null;
        
        try {                 
            os = new FileOutputStream(fileName);
            IOUtils.copy(in, os);                        
            logger.info("added new file: " + fileName);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                os.close();
            } catch (IOException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }
}
