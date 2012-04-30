/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.sshutil.command;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.IOException;
import org.apache.log4j.Logger;

/**
 * Forward port from remote machine A to Helpdesk Server to
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class ForwardPortCommand extends AbstractCommand {
    
    private static final Logger logger = Logger.getLogger(ForwardPortCommand.class);
    
    Session session;
    int lport = 5656;
    int rport= 3306;
    String remoteHost;
    
    /**
     * 
     * @param channel
     * @param lport port on remote machine
     * @param rport port on local machine
     */
    public ForwardPortCommand(Session session, String remoteHost,  int lport, int rport) {
        super(null);
        
        this.session = session;
        this.lport = lport;
        this.rport = rport;
        this.remoteHost = remoteHost;
    }
    

    @Override
    public void execute() throws JSchException, IOException{        
        
        int assinged_port = session.setPortForwardingL(lport, remoteHost , rport);
        System.out.println(assinged_port);
        
    }    
}
