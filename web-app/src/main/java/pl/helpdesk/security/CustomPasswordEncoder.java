/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.security;

import org.springframework.security.crypto.password.PasswordEncoder;



/**
 *
 * @author matik06
 */
public class CustomPasswordEncoder implements PasswordEncoder {


    @Override
    public String encode(CharSequence rawPass) {
        System.out.println("encoding password");

        return rawPass.toString();
    }

    @Override
    public boolean matches(CharSequence encPass, String rawPass) {
        System.out.println("decoding password");

        System.out.println("encPass = " + encPass);
        System.out.println("rawPass=" + rawPass);

        if (encPass.equals(rawPass)) {
            return true;
        } else {
            return false;
        }
    }
    
}
