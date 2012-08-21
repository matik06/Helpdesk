/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service;

import pl.helpdesk.model.HelpdeskUser;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public interface HelpdeskUserService extends GenericService<HelpdeskUser, Integer> {
    HelpdeskUser getByLogin(String login);
}