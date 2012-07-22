/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.managed.bean.test;

import java.util.Random;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author matik06
 */
@ManagedBean
public class Navigator {

    private String page;
    private String[] resultPages = {"page1", "page2", "page3"};

    public String choosePage() {
        Random random = new Random();
        int n = random.nextInt(3);
        
        return "/pages/basics/" + resultPages[n];
    }
    
    public String chooseDirectPage() {
        return "/pages/basics/" + page;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String[] getResultPages() {
        return resultPages;
    }

    public void setResultPages(String[] resultPages) {
        this.resultPages = resultPages;
    }
}
