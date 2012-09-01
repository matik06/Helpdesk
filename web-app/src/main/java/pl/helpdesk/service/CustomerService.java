/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service;

import java.util.List;
import pl.helpdesk.model.Customer;
import pl.helpdesk.model.HelpdeskUser;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public interface CustomerService extends GenericService<Customer, Integer> {
    List<Customer> findAll(HelpdeskUser helpdeskUser);
}
