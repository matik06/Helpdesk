/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service.impl;

import java.io.Serializable;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.helpdesk.dao.GenericDao;
import pl.helpdesk.service.GenericService;

/**
 * 
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 * @param <T> model
 * @param <ID> id
 * @param <DAO> Dao
 */
public abstract class GenericServiceImpl<T, ID extends Serializable, DAO extends GenericDao> implements GenericService<T, ID>, Serializable {
            
    @Override
    @Transactional(readOnly=true)
    public T findById(ID id) {
        return (T)getDao().findById(id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<T> findAll(int stardIndex, int fetchSize) {
        return getDao().findAll(stardIndex, fetchSize);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return getDao().findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
        return getDao().findByExample(exampleInstance, excludeProperty);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public T save(T entity) {
        return (T)getDao().save(entity);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public T update(T entity) {
        return (T)getDao().update(entity);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void delete(T entity) {
        getDao().delete(entity);
    }
    
    
    public abstract DAO getDao();
}
