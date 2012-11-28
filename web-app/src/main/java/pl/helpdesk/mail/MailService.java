/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.mail;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import pl.helpdesk.model.User;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service("mailService")
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS)
public class MailService implements Serializable {
    
    @Autowired
    private transient MailSender mailSender;
    
    @Value("${mail.smtp.from}")
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
             if (recipient != null) {
                 message.setTo(recipient.getEmail());
                 message.setSubject(subject);
                 message.setText(body);

                 mailSender.send(message);
             }                          
         }                  
     }
}