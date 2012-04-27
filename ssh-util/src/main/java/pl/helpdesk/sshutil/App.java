package pl.helpdesk.sshutil;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * http://www.time4tea.net/wiki/display/MAIN/SCP+a+file+in+Java+using+JSch
 *
 */
public class App 
{
    private static final String USER_NAME = "matik06";
    private static final String HOST = "d3vil0.no-ip.org";
    private static final int PORT = 22;
    private static final String PASSWORD = "zzxxcc";
    
    public static void main( String[] args ) throws JSchException, IOException, SftpException
    {
        System.out.println("Test połączenia ssh: ");
        
        JSch jsch = new JSch();
        Session session = jsch.getSession(USER_NAME, HOST, PORT);
        session.setPassword(PASSWORD);        
        session.setConfig("StrictHostKeyChecking", "no");
        
        session.connect();
                
        boolean isConnected = session.isConnected();
        System.out.println(isConnected);
        
        
        
        //**********************************************************************/
        File localFile = new File("/home/matik/test_result.txt");
        ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
        channel.connect();
        channel.cd("./");
        channel.put(new FileInputStream(localFile), "test2.txt");
        channel.disconnect();
        /**********************************************************************/
        
        session.disconnect();        
        System.out.println("Koniec testów :)");
    }
}
