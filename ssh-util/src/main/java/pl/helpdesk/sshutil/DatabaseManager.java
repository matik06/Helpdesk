/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.sshutil;

import java.io.IOException;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class DatabaseManager {

    public static boolean backup(String mysqldumpPath, String backupPath) {
        boolean status = false;
        String username = "name";
        String password = "pword";
        String database = "database_name";


        String command = "/" + mysqldumpPath + "/mysqldump -u " + username + " -p" + password + " " + database + " -r " + backupPath;
        try {
            Process runtimeProcess = Runtime.getRuntime().exec(command);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                System.out.println("DatabaseManager.backup: Backup Successfull");
                status = true;
            } else {
                System.out.println("DatabaseManager.backup: Backup Failure!");
            }
        } catch (IOException ioe) {
            System.out.println("Exception IO");
            ioe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception");
            e.printStackTrace();
        }
        return status;
    }

    public static boolean restore(String filePath) {
        boolean status = false;
        String username = "name";
        String password = "pword";
        String[] command = new String[]{"mysql", "database_name", "-u" + username, "-p" + password, "-e", " source " + filePath};

        try {
            Process runtimeProcess = Runtime.getRuntime().exec(command);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                System.out.println("DatabaseManager.restore: Restore Successfull");
                status = true;
            } else {
                System.out.println("DatabaseManager.restore: Restore Failure!");
            }
        } catch (IOException ioe) {
            System.out.println("Exception IO");
            ioe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception");
            e.printStackTrace();
        }
        return status;
    }

//for testing
    public static void main(String args[]) {
        String backupName = "D:/DatabaseBackup/backupHvs.sql";
        DatabaseManager.restore(backupName);
    }
}