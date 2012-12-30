/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service;

import java.util.List;
import pl.helpdesk.model.Customer;
import pl.helpdesk.model.Upgrade;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public interface UpgradeService extends GenericService<Upgrade, Integer> {
    public List<Upgrade> findAll(boolean isCompleted);
    public List<Upgrade> findAll(Customer customer, boolean isCompleted);
}