/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.springframework.security.access.annotation.Secured;
import pl.helpdesk.model.BaseEntity;
import pl.helpdesk.service.GenericService;

/**
 *
 * @author matik06
 */
public abstract class GridController<T extends BaseEntity<Integer>> extends BaseController {
        
    private Class<T> clazz;
    
    protected List<T> entityList;
    protected T entity;
    protected T selectedEntity;    
    protected boolean permissionToWrite = true;
    
    private static final Logger logger = Logger.getLogger(GridController.class);
    
    public abstract GenericService<T, Integer> getService();
    
    public GridController(Class<T> clazz) {
        this.clazz = clazz;
        try {
            entity = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
    
    @PostConstruct
    public void init() throws InstantiationException, IllegalAccessException {
        reloadList();
    }        
        
    public void saveOrUpdateEntity() {

        System.out.println("save or update");

        if (entity.getId() == null) {
            getService().save(entity);
        } else {
            getService().update(entity);
        }

        reloadList();
    }

    public void deleteEntity() {

        System.out.println("delete ");
        System.out.println(entity);
        getService().delete(entity);        
        reloadList();
    }    
    
    protected void reloadList() {
        entityList = getService().findAll();
    }

    public void clear() throws InstantiationException, IllegalAccessException {
        this.entity = clazz.newInstance();
    }

    public void onRowSelect(SelectEvent event) {
        this.selectedEntity = (T) event.getObject();
    }

    public boolean isPermissionToWrite() {
        return permissionToWrite;
    }

    public void enablePermissionToWrite() {
        permissionToWrite = true;
    }

    public void disablePermissionToWrite() {
        permissionToWrite = false;
    }

    public T getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(T selectedEntity) {
        this.selectedEntity = selectedEntity;
    }

    public List<T> getEntityList() {
        return entityList;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}
