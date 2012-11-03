/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller;

import java.io.InputStream;
import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import pl.helpdesk.model.File;
import pl.helpdesk.model.TaskFile;
import pl.helpdesk.service.TaskFileService;
import pl.helpdesk.util.HelpdeskFileUtil;

/**
 *
 * @author matik06
 */
@Controller
@Scope(value = "request")
public class DownloadController {
    
    private static final Logger logger = Logger.getLogger(DownloadController.class);
    
    private Integer fileId;
    private StreamedContent file;
    
    @Autowired HelpdeskFileUtil fileUtil;
    @Autowired TaskFileService taskFileService;
    
    public void downloadTaskFile() {
        TaskFile helpdeskFile = taskFileService.findById(fileId);
        download(helpdeskFile);
    }
    
    public String download(File file) {                        
        
        InputStream is = fileUtil.getFileStream(file.getFileSystemName());
        this.file = new DefaultStreamedContent(is, file.getContentType(), file.getName());
        logger.info("preparing file for downloading: " + file.getFileSystemName());
        
        return null;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }    

    public StreamedContent getFile() {
        logger.info("downloading file: " + file);
        if (file != null) {
            logger.info(file.getName());
        }
        return file;
    }        
}
