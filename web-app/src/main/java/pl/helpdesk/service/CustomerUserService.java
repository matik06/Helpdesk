/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service;

import java.util.List;
import pl.helpdesk.model.Customer;
import pl.helpdesk.model.CustomerUser;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public interface CustomerUserService extends GenericService<CustomerUser, Integer> {
    CustomerUser getByLogin(String login);
    List<CustomerUser> getProjectManagerList(Customer customer);
    List<CustomerUser> getCustomerUsers(Customer customer);
}
