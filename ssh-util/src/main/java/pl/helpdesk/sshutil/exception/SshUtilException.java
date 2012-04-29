/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.sshutil.exception;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class SshUtilException extends RuntimeException {

    public SshUtilException(Throwable cause) {
        super(cause);
    }

    public SshUtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public SshUtilException(String message) {
        super(message);
    }

    public SshUtilException() {
        super();
    }
    
}
