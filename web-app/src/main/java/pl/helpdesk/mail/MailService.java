/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service("mailService")
public class MailService {
    
    @Autowired
    private MailSender mailSender;

     public void sendMail(String from, String to, String subject, String body) {
         
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
     }
}