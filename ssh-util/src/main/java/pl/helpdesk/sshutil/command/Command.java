/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.sshutil.command;

import com.jcraft.jsch.JSchException;
import java.io.IOException;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public interface Command {
    void execute() throws JSchException, IOException;
}
