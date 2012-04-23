/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import pl.helpdesk.dao.GenericDao;
import pl.helpdesk.service.GenericService;

/**
 * 
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 * @param <T> model
 * @param <ID> id
 * @param <DAO> Dao
 */
public abstract class GenericServiceImpl<T, ID extends Serializable, DAO extends GenericDao> implements GenericService<T, ID> {
    
    @Autowired
    protected DAO dao;
    

    @Override
    public T findById(ID id) {
        return (T)dao.findById(id);
    }

    @Override
    public List<T> findAll(int stardIndex, int fetchSize) {
        return dao.findAll(stardIndex, fetchSize);
    }

    @Override
    public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
        return dao.findByExample(exampleInstance, excludeProperty);
    }

    @Override
    public T save(T entity) {
        return (T)dao.save(entity);
    }

    @Override
    public T update(T entity) {
        return (T)dao.update(entity);
    }

    @Override
    public void delete(T entity) {
        dao.delete(entity);
    }
    
    
}
