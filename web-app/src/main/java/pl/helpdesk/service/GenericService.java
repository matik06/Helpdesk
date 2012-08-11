/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.service;

import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.Criterion;
import pl.helpdesk.model.BaseEntity;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public interface GenericService <T, ID extends Serializable> {
    
    T findById(ID id);
    List<T> findAll(int stardIndex, int fetchSize);
    List<T> findAll();
    List<T> findAllByRestriction(Criterion... criterions);
    List<T> findAllByIds(Integer... ids);
    List<T> findByExample(T exampleInstance, String... excludeProperty);    
    T save(T entity);
    T update(T entity);
    void delete(T entity);       
}