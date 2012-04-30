/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.sshutil.common;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public class AvailableProtFinder {
    
    private static final int MAX = 65000;
    private static final int MIN = 50001;
    
    
    public static int getNextAvailable() {
        
        while (true) {
            //losujemy port z danego zakresu
            int randPort = MIN + (int)(Math.random() * ((MAX - MIN) + 1));
            
            //sprawdzamy czy port jest wolny
            if (available(randPort)) {
                return randPort;
            }
        }        
    }
    
    /**
     * Checks to see if a specific port is available.
     *
     * @param port the port to check for availability
     */
    public static boolean available(int port) {
        if (port < 50000 || port > 65000) {
            throw new IllegalArgumentException("Invalid start port: " + port);
        }

        ServerSocket ss = null;
        DatagramSocket ds = null;
        try {
            ss = new ServerSocket(port);
            ss.setReuseAddress(true);
            ds = new DatagramSocket(port);
            ds.setReuseAddress(true);
            return true;
        } catch (IOException e) {
        } finally {
            if (ds != null) {
                ds.close();
            }

            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                    /* should not be thrown */
                }
            }
        }

        return false;
    }    
}
