package pl.helpdesk.sshutil;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.UserInfo;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * http://www.time4tea.net/wiki/display/MAIN/SCP+a+file+in+Java+using+JSch
 *
 */
public class App 
{
    
    private static final String PRIVATE_KEY = "/home/matik/.ssh/id_rsa";
    
    private static final String USER_NAME = "matik06";
    private static final String HOST = "d3vil0.no-ip.org";
    private static final int PORT = 22;
    private static final String PASSWORD = "zzxxcc";
    
    public static void main( String[] args ) throws JSchException, IOException, SftpException
    {
        System.out.println("Test połączenia ssh: ");
        
        JSch jsch = new JSch();
        jsch.addIdentity(PRIVATE_KEY);
        
        Session session = jsch.getSession(USER_NAME, HOST, PORT);
//        session.setPassword(PASSWORD);        
        session.setConfig("StrictHostKeyChecking", "no");
        
        session.connect();
                
        boolean isConnected = session.isConnected();
        System.out.println(isConnected);
      
        
        //**********************************************************************/
        //JSch - port forwarding
        int lport = 5656;
        int rport = 3306;

        int assigned_port = session.setPortForwardingL(lport, "localhost", rport);
        System.out.println("Port fowrwared "+ assigned_port);
        
        new Scanner(System.in).next();
        
        //**********************************************************************/
        ChannelExec exec = (ChannelExec) session.openChannel("exec");
//        exec.setCommand("touch ppp.txt");
        exec.connect();
        
        
        //**********************************************************************/
        
        ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
        channel.connect();
                                        
        
        //zmiana nazwy pliku lub zmiana jego lokalizacji
//        channel.rename("test3.txt", "./test/text3.txt");
        
        
//        final Vector files = channel.ls(".");
//        for (Object obj : files) {
//            System.out.println(obj.toString());
//            // Do stuff with files
//        }
        
        
        exec.disconnect();
        channel.disconnect();
        /**********************************************************************/
        //mysql backup:
        //mysqldump -u [Username] -p [password] [databasename] > [backupfile.sql]
        
        //mysql restore:
        //mysql - u admin -p admin accounts < accounts.sql
        //Runtime.getRuntime().exec("mysql -u USERNAME -pPASSWORD DBNAME <  D:/backup/bkup.sql");
        
        //In java:
        //String executeCmd = “mysqldump -u “+dbUser+” -p”+dbPass+” “+dbName+” -r backup.sql”;
//        Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
//        int processComplete = runtimeProcess.waitFor();
//        if (processComplete == 0) {
//            out.println(“Backup taken successfully”);
//        } else {
//            out.println(“Could not take mysql backup”);
//        }
        
//        try {
//            String cmd =
//                    "C:\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysqldump.exe";
//            Runtime rt = Runtime.getRuntime();
//            Process proc = rt.exec(new String[]{cmd, "-uroot", "-proot", "foo_db",
//                        "-rC:\\backup.sql"});
//            // wait for it to finish
//            proc.waitFor();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        
        //JSch - port forwarding
//        int lport=5656;
//        int rport=3306;
//        String rhost = "secure.journaldev.com";
//        String host = "secure.journaldev.com";
//        session.connect();
//        System.out.println("Connected");
//        int assinged_port = session.setPortForwardingL(lport, rhost, rport);
//        System.out.println("localhost:" + assinged_port + " -> " + rhost + ":" + rport);
//        System.out.println("Port Forwarded");
        
        /**********************************************************************/
        
        session.disconnect();        
        System.out.println("Koniec testów :)");
    }
    
    private static void download(ChannelSftp channelSftp) throws FileNotFoundException, SftpException {
//        byte[] buffer = new byte[1024];
//        BufferedInputStream bis = new BufferedInputStream(channelSftp.get("Test.java"));
//        File newFile = new File("C:/Test.java");
//        OutputStream os = new FileOutputStream(newFile);
//        BufferedOutputStream bos = new BufferedOutputStream(os);
//        int readCount;
//        //System.out.println("Getting: " + theLine);
//        while( (readCount = bis.read(buffer)) &gt; 0) {
//            System.out.println("Writing: ");
//            bos.write(buffer, 0, readCount);
//        }
    }
    
    
    class localUserInfo implements UserInfo {

        String passwd;

        public String getPassword() {
            return passwd;
        }

        public boolean promptYesNo(String str) {
            return true;
        }

        public String getPassphrase() {
            return null;
        }

        public boolean promptPassphrase(String message) {
            return true;
        }

        public boolean promptPassword(String message) {
            return true;
        }

        public void showMessage(String message) {
        }
    }
}
