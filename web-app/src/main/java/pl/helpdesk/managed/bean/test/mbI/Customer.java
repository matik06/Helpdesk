/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.managed.bean.test.mbI;

/**
 *
 * @author matik06
 */
public class Customer {
    
    private String id;
    private Double balanceNoSign;
    private String password;
    private String firstName;
    private String lastName;

    public Customer(String id, String firstName, String lastName, Double balance) {
        this.id = id;
        this.balanceNoSign = balance;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Double getBalanceNoSign() {
        return balanceNoSign;
    }

    public void setBalanceNoSign(Double balanceNoSign) {
        this.balanceNoSign = balanceNoSign;
    }
        

  

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
