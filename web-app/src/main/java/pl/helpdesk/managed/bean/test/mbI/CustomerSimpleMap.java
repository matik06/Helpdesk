/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.managed.bean.test.mbI;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author matik06
 */
public class CustomerSimpleMap implements CustomerLookupService {
    
    private Map<String, Customer> customers;

    public CustomerSimpleMap() {
        customers = new HashMap<>();
        addCustomer(new Customer("id001", "Harry",
                "Hacker", -3456.78));
        addCustomer(new Customer("id002", "Codie",
                "Coder", 1234.56));
        addCustomer(new Customer("id003", "Polly",
                "Programmer", 987654.32));
    }
    
    @Override
    public Customer findCustomer(String id) {
        if (id != null) {
            return (customers.get(id.toLowerCase()));
        } else {
            return (null);
        }
    }

    private void addCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }
}
