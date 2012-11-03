/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.mail;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import pl.helpdesk.model.User;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service("mailService")
public class MailService {
    
    @Autowired
    private MailSender mailSender;
    
    //todo @Value(spel...)
    private String from;

     public void sendMail(String to, String subject, String body) {
         
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
     }
     
     public void sendMail(List<User> recipients, String subject, String body) {
         
         for (User recipient : recipients) {
             SimpleMailMessage message = new SimpleMailMessage();


             message.setFrom(from);
             message.setTo(recipient.getEmail());
             message.setSubject(subject);
             message.setText(body);

             mailSender.send(message);   
         }                  
     }
}