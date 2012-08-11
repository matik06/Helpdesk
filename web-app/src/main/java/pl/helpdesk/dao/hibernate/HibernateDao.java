/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao.hibernate;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.helpdesk.dao.GenericDao;
import pl.helpdesk.model.BaseEntity;

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
    public T findById(ID id) {
        return (T) getSession().get(persistentClass, id);
    }

    @Override
    public T save(T entity) {
        getSession().save(entity);
        return entity;
    }
    
    @Override
    public T update(T entity) {
        getSession().update(entity);
        return entity;
    }

    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }

    @Override
    public List<T> findAll(int stardIndex, int fetchSize) {
        Criteria crit = getSession().createCriteria(persistentClass);

        crit.setFirstResult(stardIndex);
        crit.setFetchSize(fetchSize);

        return crit.list();
    }
    
    @Override
    public List<T> findAll() {
        Criteria crit = getSession().createCriteria(persistentClass);
        return crit.list();
    }

    @Override
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
    
    @Override
    public List<T> findAllByRestriction(Criterion... criterions) {        
        Criteria criteria = getSession().createCriteria(persistentClass);
        
        for (Criterion criterion : criterions) {
            criteria.add(criterion);
        }
        
        return criteria.list();
    }

    @Override
    public List<T> findAllByIds(Integer... ids) {
        
        List<Integer> idList = Arrays.asList(ids);                
        
        Query query = getSession().createQuery("FROM " + persistentClass.getName() + " item WHERE item.id IN :ids");
        query.setParameterList("ids", idList);
        
        return query.list();
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
