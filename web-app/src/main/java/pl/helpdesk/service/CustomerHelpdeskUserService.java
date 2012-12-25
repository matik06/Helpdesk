/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service;

import java.util.List;
import pl.helpdesk.model.Customer;
import pl.helpdesk.model.CustomerHelpdeskUser;
import pl.helpdesk.model.HelpdeskUser;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public interface CustomerHelpdeskUserService extends GenericService<CustomerHelpdeskUser, Integer> {
    List<HelpdeskUser> getProjectManagerList(Customer customer);
}