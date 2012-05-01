/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.sshutil.command.complex;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import org.apache.log4j.Logger;
import pl.helpdesk.sshutil.command.Command;
import pl.helpdesk.sshutil.common.SshUtil;
import pl.helpdesk.sshutil.common.User;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
abstract class ComplexCommand {
    
    private static final Logger logger = Logger.getLogger(ComplexCommand.class);
    
    protected Session serverSession;
    protected ChannelSftp serverSftpChannel;
    protected ChannelExec serverExecChannel;
    
    protected Session operatorSession;
    protected ChannelSftp operatorSftpChannel;
    protected ChannelExec operatorExecChannel;
    
    protected File temporaryDirectory;
    
    protected abstract List<Command> initialize();
    
    public ComplexCommand(User serverUser, User operatorUser) throws JSchException {
        
        //przekierowanie strumienia err do loggera: logger.error(...);
//        System.setErr(createLoggingProxy(System.err));
        
        this.serverSession = initSession(serverUser, serverSession);
        this.serverExecChannel = (ChannelExec) serverSession.openChannel("exec");        
        this.serverExecChannel.setErrStream(System.err);                
        this.serverSftpChannel = (ChannelSftp) serverSession.openChannel("sftp");
                
        this.operatorSession = initSession(operatorUser, operatorSession);
        this.operatorExecChannel = (ChannelExec) operatorSession.openChannel("exec");
        this.operatorExecChannel.setErrStream(System.err);                
        this.operatorSftpChannel = (ChannelSftp) operatorSession.openChannel("sftp");
        
        //utworzenie tymczasowego folderu
        this.temporaryDirectory = SshUtil.INSTANCE.createTemporaryDirectory();
    }
    
    private Session initSession(User user, Session session) throws JSchException {
        JSch jsch = new JSch();
        
        if (user.getIsAuthByKeys()) {
            session = authByKey(jsch, user);            
        } else {
            session = authByPassword(jsch, user);
        }
        
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        
        return session;
    }
    
    
    private Session authByKey(JSch jsch, User user) throws JSchException {

        jsch.addIdentity(user.getPrivateKeyPath());        
        Session session = jsch.getSession(
                user.getUsername(),
                user.getHost(),
                user.getPort());
        
        return session;
    }
    
    private Session authByPassword(JSch jsch, User user) throws JSchException {
        
        Session session = jsch.getSession(
                user.getUsername(),
                user.getHost(),
                user.getPort());
        session.setPassword(user.getPassword());
        
        return session;
    }
    
    public static PrintStream createLoggingProxy(final PrintStream realPrintStream) {
        return new PrintStream(realPrintStream) {

            public void print(final String string) {
                realPrintStream.print(string);
                logger.error(string);
            }
        };
    }
    

    public void execute() throws JSchException, IOException {
        
        List<Command> commands = initialize();  
        connectSftp();
        
        for (Command command : commands) {
            command.execute();
        }
        
        //wczytanie linijki tekstu przes wykonaniem dalszej części kodu
        new Scanner(System.in).next();
        
        //usuniecie folderu tymczasowego
        SshUtil.INSTANCE.deleteDirectory(temporaryDirectory);
        //rozałcze
        disconnect();
    }
    
    private void connectSftp() throws JSchException {        

            this.serverSftpChannel.connect();
            this.operatorSftpChannel.connect();
    }
    
    private void connectExec() throws JSchException {

        this.serverExecChannel.connect();
            this.operatorExecChannel.connect();        
    }
    
    private void disconnect() {
        
        disconnectChannel(this.serverExecChannel);
        disconnectChannel(this.serverSftpChannel);
        this.serverSession.disconnect();
        
        disconnectChannel(this.operatorExecChannel);
        disconnectChannel(this.operatorSftpChannel);        
        this.operatorSession.disconnect();
    }
    
    private void disconnectChannel(Channel channel) {
        if (channel.isConnected()) {
            channel.disconnect();
        }
    }
}
