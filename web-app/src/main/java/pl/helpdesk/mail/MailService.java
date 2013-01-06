/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.mail;

import java.io.Serializable;
import java.util.List;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pl.helpdesk.model.User;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Service("mailService")
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS)
public class MailService implements Serializable {
    
    private Logger logger = Logger.getLogger(MailService.class);
    
    public MailService() {
        System.setProperty("mail.mime.charset", "utf8");
    }
    
    @Autowired
    private transient JavaMailSender mailSender;
    
    @Value("${mail.smtp.from}")
    private String from;

     public void sendMail(String to, String subject, String body) {        
        
        MimeMessage mimeMessage = mailSender.createMimeMessage();        
        
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject("Helpdesk: " + subject);
            helper.setText("<html><body>" + body + "</body></html>", true);
            logger.info(body);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
        }

        try {
            mailSender.send(mimeMessage);
        } catch (MailException e) {
            logger.info(e.getMessage(), e);
        }
     }
     
     public void sendMail(List<? extends User> recipients, String subject, String body) {
         
         for (User recipient : recipients) {
             
             if (recipient != null) {
                 sendMail(recipient.getEmail(), subject, body);
             }                       
         }                  
     }
}