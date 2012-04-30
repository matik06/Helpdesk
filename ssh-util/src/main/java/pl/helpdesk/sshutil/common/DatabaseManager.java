/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.sshutil.common;

import java.io.IOException;
import org.apache.log4j.Logger;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public abstract class DatabaseManager {
    
    public static final Logger logger = Logger.getLogger(DatabaseManager.class);
    
    protected String username;
    protected String password;
    protected String database;
    protected Integer port;
    
    protected abstract String getBackupCommand();
    
    public DatabaseManager(String username, String password, String database, int port) {
        this.username = username;
        this.password = password;
        this.database = database;
        this.port = port;
    }
    
    public boolean backup(String backupPath) throws IOException, InterruptedException {
        
        boolean status = false;               
        String command = getBackupCommand();        
        Process runtimeProcess = Runtime.getRuntime().exec(command);        
        int processComplete = runtimeProcess.waitFor();
        logger.debug("username: " + username + " database: " + database + " port: " + port + " backup file: " + backupPath);
        
        if (processComplete == 0) {
            logger.debug("DatabaseManager.backup: Backup Successfull");
            status = true;
        } else {
            logger.error("DatabaseManager.backup: Backup Failure!");
        }
        
        return status;
    }

    public boolean restore(String filePath) {
        
        throw new UnsupportedOperationException();
        
//        boolean status = false;
//        String[] command = new String[]{"mysql", "database_name", "-u" + username, "-p" + password, "-e", " source " + filePath};
//
//        try {
//            Process runtimeProcess = Runtime.getRuntime().exec(command);
//            int processComplete = runtimeProcess.waitFor();
//            if (processComplete == 0) {
//                System.out.println("DatabaseManager.restore: Restore Successfull");
//                status = true;
//            } else {
//                System.out.println("DatabaseManager.restore: Restore Failure!");
//            }
//        } catch (IOException ioe) {
//            System.out.println("Exception IO");
//            ioe.printStackTrace();
//        } catch (Exception e) {
//            System.out.println("Exception");
//            e.printStackTrace();
//        }
//        return status;
    }
}