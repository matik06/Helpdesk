/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller;

import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author matik06
 */
@Controller
@Scope(value = "view")
public class TaskCustomerMyOpenController extends CustomerTaskController implements Serializable {
    @Override
    protected void reloadList() {
        this.entityList = service.findOpen(customer, getLoggedUser());
    }
}
