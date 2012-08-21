/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.security;

import java.io.IOException;
import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import pl.helpdesk.util.FacesUtils;

/**
 *
 * @author matik06
 */
@Controller
@Scope("request")
public class LoginBean {
    //managed properties for the login page, username/password/etc...
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    @Autowired
    private AuthenticationServiceImpl authenticationService; // injected Spring defined service for bikes
    
    public void setPassword(String password) {
        this.password = password;
    }        

    // This is the action method called when the user clicks the "login" button
    public String doLogin() throws IOException, ServletException {
        
        boolean success = authenticationService.login(login, password);
        if (success) {
            System.out.println("zalogowano poprawnie");
            return "/index.xhtml?faces-redirect=true";
//            return "bikesShop.xhtml"; // return to application but being logged now
        } else {
            FacesUtils.addErrorMessage("Hasło lub login niepoprawne");
            return "/login.xhtml";
        }
        
//        FacesContext.getCurrentInstance().responseComplete();
//        // It's OK to return null here because Faces is just going to exit.
//        return null;
        
//        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
//
//        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
//                .getRequestDispatcher("/j_spring_security_check?j_username=" + login
//                                + "&j_password=" + password);
//        //RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/j_spring_security_check");
//
//        dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
//
//        FacesContext.getCurrentInstance().responseComplete();
//        // It's OK to return null here because Faces is just going to exit.
//        return null;
    }
}
