/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.managed.bean.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author matik06
 */
@ManagedBean(name="userList")
@ViewScoped
public class UserList implements Serializable{
 
   private ArrayList<String> users;
   private String selectedUser;
   private boolean register = false;
   
   //parametry strony:
   private int id = 101;
   private String text = "category_text";
 
   @PostConstruct
   public void create (){
	users = new ArrayList <String> ();
	users.add("John");
	users.add("Charley");
	users.add("Priscila");
	users.add("Kate");
	users.add("Emily");
	users.add("Barack");
	users.add("Mia");
	users.add("Arthur");
   }
   public void delete (){
	users.remove(selectedUser);
   }
   public String getSelectedUser() {
	return selectedUser;
   }
   public void setSelectedUser(String selectedUser) {
	this.selectedUser = selectedUser;
   }
   public ArrayList<String> getUsers() {
	return users;
   }

    public boolean isRegister() {
        return register;
    }

    public void setRegister(boolean register) {
        this.register = register;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }        
      
   
   public String nextPage() {
       return "page2";
   }
   
   public String nextPage3() {
       return "page3";
   }
   
   public boolean navigate() {
       return true;
   }
   
    public void loadAction() {
        List<FacesMessage> messages = FacesContext.getCurrentInstance().getMessageList();

        if (messages.isEmpty()) {
            //wykonujemy pewną inicjalizację jeśli nie ma żadnych wiadomości z błędami dotyczącymi parametrów url
        }
    }
}