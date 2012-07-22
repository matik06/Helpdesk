/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.managed.bean.test.mbI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.faces.model.SelectItem;

/**
 *
 * @author matik06
 */
public class LanguageUtils {

    private static final String[] languages = {"Java", "JavaScript", "C#", "C++", "PHP", "Python",
        "Perl", "Ruby", "Scala"};
    private static List<SelectItem> availableLanguages;

    static {
        availableLanguages = new ArrayList<>();
        for (String language : languages) {
            availableLanguages.add(new SelectItem(language));
        }
    }

    public static List<SelectItem> languageList() {
        return (availableLanguages);    
    }
 
    public static String findMostPopularLanguage(int i) {
        return languages[i];
    }
    
    public static List<String> randomLanguages(int n) {
        List<String> result = new ArrayList<>();
        
        Random random = new Random();
        
        for (int i = 0; i < n; i++) {
            int id = random.nextInt(languages.length);
            result.add(languages[id]);            
        }
        
        return result;
    }
}