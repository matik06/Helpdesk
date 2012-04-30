/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.sshutil.command;

import com.jcraft.jsch.JSchException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Create empty directory on remote machine
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class CreateDirectoryLocallyCommand extends AbstractCommand {
    
    private static final Logger logger = Logger.getLogger(CreateDirectoryLocallyCommand.class);
    
    private File localDirectory;
    
    public CreateDirectoryLocallyCommand(File localDirectory) {
        super(null);
        this.localDirectory = localDirectory;
    }
    

    @Override
    public void execute() throws JSchException, IOException{        
        
        List<File> parentDirectories = getParentFolders(localDirectory);

        //stworzenie wczesniejszych katalogow jesli nie istnialy
        for (int i = parentDirectories.size() - 1; i >= 0; i--) {
            File f = parentDirectories.get(i);                    
            f.mkdir();                    
        }                        

        //sprawdzamy czy udalo sie utworzyc glowny katalog
        boolean success = localDirectory.mkdir();
        if (!success) {
            throw new IOException("Can't create local directory: " + localDirectory.getAbsolutePath());
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
