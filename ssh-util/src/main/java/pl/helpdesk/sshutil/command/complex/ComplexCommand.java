/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.sshutil.command.complex;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import pl.helpdesk.sshutil.command.Command;
import pl.helpdesk.sshutil.common.FolderManager;
import pl.helpdesk.sshutil.common.User;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
abstract class ComplexCommand {
    
    protected Session serverSession;
    protected ChannelSftp serverSftpChannel;
    protected ChannelExec serverExecChannel;
    
    protected Session operatorSession;
    protected ChannelSftp operatorSftpChannel;
    protected ChannelExec operatorExecChannel;
    
    protected File temporaryDirectory;
    
    protected abstract List<Command> initialize();
    
    public ComplexCommand(User serverUser, User operatorUser) throws JSchException {
        
        this.serverSession = initSession(serverUser, serverSession);
        this.serverExecChannel = (ChannelExec) serverSession.openChannel("exec");
        this.serverSftpChannel = (ChannelSftp) serverSession.openChannel("sftp");
                
//        this.operatorSession = initSession(operatorUser, operatorSession);
//        this.operatorExecChannel = (ChannelExec) operatorSession.openChannel("exec");
//        this.operatorSftpChannel = (ChannelSftp) operatorSession.openChannel("sftp");
        
        
        //utworzenie tymczasowego folderu
        this.temporaryDirectory = FolderManager.INSTANCE.createTemporaryDirectory();
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
    

    public void execute() throws JSchException {
        
        List<Command> commands = initialize();        
        connect();
        
        for (Command command : commands) {
            command.execute();
        }
        
        //wczytanie linijki tekstu przes wykonaniem dalszej części kodu
        new Scanner(System.in).next();
        
        //usuniecie folderu tymczasowego
        FolderManager.INSTANCE.deleteDirectory(temporaryDirectory);
        //rozałcze
        disconnect();
    }
    
    private void connect() throws JSchException {        
            this.serverExecChannel.connect();
            this.serverSftpChannel.connect();
            
//            this.operatorExecChannel.connect();
//            this.operatorSftpChannel.connect();
    }
    
    private void disconnect() {
        this.serverExecChannel.disconnect();
        this.serverSftpChannel.disconnect();
        this.serverSession.disconnect();
        
//        this.operatorExecChannel.disconnect();
//        this.operatorSftpChannel.disconnect();
//        this.operatorSession.disconnect();
    }
}
