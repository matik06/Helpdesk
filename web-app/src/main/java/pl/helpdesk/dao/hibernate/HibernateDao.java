/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.helpdesk.dao.GenericDao;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
@Repository
public abstract class HibernateDao<T, ID extends Serializable> implements GenericDao<T, ID> {
    
    @Autowired
    SessionFactory sessionFactory;
    private Class<T> persistentClass;
    

    public HibernateDao(Class c) {
        this.persistentClass = c;
    }

    @Override
    @Transactional(readOnly = true) 
    public T findById(ID id) {
        return (T) getSession().load(persistentClass, id);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public T save(T entity) {
        getSession().save(entity);
        return entity;
    }
    
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public T update(T entity) {
        getSession().update(entity);
        return entity;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void delete(T entity) {
        getSession().delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll(int stardIndex, int fetchSize) {
        Criteria crit = getSession().createCriteria(persistentClass);

        crit.setFirstResult(stardIndex);
        crit.setFetchSize(fetchSize);

        return crit.list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findByExample(T exampleInstance, String... excludeProperty) {

        Criteria crit = getSession().createCriteria(persistentClass);
        Example example = Example.create(exampleInstance);

        if (excludeProperty != null && excludeProperty instanceof String[]) {
            for (int i = 0; i < excludeProperty.length; i++) {
                example.excludeProperty(excludeProperty[i]);
            }
        }

        crit.add(example);
        return crit.list();
    }
    
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    public void beginTransaction() {
        beginTransaction();
    }

    public void commitTransaction() {
        commitTransaction();
    }
}
