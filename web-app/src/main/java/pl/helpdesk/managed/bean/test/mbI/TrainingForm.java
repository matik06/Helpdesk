/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.managed.bean.test.mbI;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

/**
 *
 * @author matik06
 */
@ManagedBean
public class TrainingForm {

    private String emailAddress;
    private String favoriteLanguage =
            LanguageUtils.findMostPopularLanguage(0);
    private String secondFavoriteLanguage =
            LanguageUtils.findMostPopularLanguage(1);
    private boolean isExpert = true;
    private boolean isLiar = false;
// Getters and setters for all. The getters for
// the boolean properties start with is, not get
    public List<SelectItem> getAvailableLanguages() {
        return (LanguageUtils.languageList());
    }  // Options for dropdown box. See later slide.
    
    public String showTrainingPlan() {
        int numLanguagesToStudy;
        if (isExpert) {
            numLanguagesToStudy = 4;
        } else {
            numLanguagesToStudy = 2;
        }
        if (isLiar) {
            return ("liar");
        } else {
            languagesToStudy =
                    LanguageUtils.randomLanguages(numLanguagesToStudy);
            return ("/pages/mbI/study-plan.xhtml");
        }
    }
    
    private List<String> languagesToStudy;

    public List<String> getLanguagesToStudy() {
        return (languagesToStudy);
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFavoriteLanguage() {
        return favoriteLanguage;
    }

    public void setFavoriteLanguage(String favoriteLanguage) {
        this.favoriteLanguage = favoriteLanguage;
    }

    public boolean getIsExpert() {
        return isExpert;
    }

    public void setIsExpert(boolean isExpert) {
        this.isExpert = isExpert;
    }

    public boolean getIsLiar() {
        return isLiar;
    }

    public void setIsLiar(boolean isLiar) {
        this.isLiar = isLiar;
    }

    public String getSecondFavoriteLanguage() {
        return secondFavoriteLanguage;
    }

    public void setSecondFavoriteLanguage(String secondFavoriteLanguage) {
        this.secondFavoriteLanguage = secondFavoriteLanguage;
    }            
}