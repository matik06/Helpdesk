/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.sshutil.command;

import com.jcraft.jsch.Channel;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public abstract class AbstractCommand implements Command {
    
    protected Channel channel;

    public AbstractCommand(Channel channel) {
        this.channel = channel;
    }        
}
