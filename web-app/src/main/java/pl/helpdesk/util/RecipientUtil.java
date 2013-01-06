/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.util;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import pl.helpdesk.model.User;

/**
 * Klasa przeznaczona do zarzadzania lista osob dla ktorych zostanie wyslane powiadomienie (mail)
 * W pozniejszej fazie kazdy uzytkownik bedzie mogl zdefiniowac jakiego typu wiadomosci chce otrzymywac
 *
 * @author MAT1K
 */
public class RecipientUtil {
    private Set<User> recipients = new LinkedHashSet<>();
    
    public RecipientUtil() {
        
    }
    
    public void add(List<? extends User> recipientList) {
        recipients.addAll(recipientList);
    }
    
    public void add(User recipient) {
        recipients.add(recipient);
    }
    
    public void add(String email) {
        User user = new User();
        user.setEmail(email);
        recipients.add(user);
    }
    
    public List<User> getRecipientList() {
        //ewentualna filtracja
        return new ArrayList(recipients);
    }
}
