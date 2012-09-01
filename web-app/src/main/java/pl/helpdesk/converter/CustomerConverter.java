/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.helpdesk.model.Customer;
import pl.helpdesk.service.CustomerService;

/**
 *
 * @author matik06
 */
@Component
public class CustomerConverter extends BaseConverter<Customer> {

    @Autowired
    public CustomerConverter(CustomerService service) {
        super(service);
    }
    
}
