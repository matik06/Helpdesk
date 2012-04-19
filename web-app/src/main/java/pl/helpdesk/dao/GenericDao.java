/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public interface GenericDao<T, ID extends Serializable> {
    
    T findById(ID id);
    List<T> findAll(int stardIndex, int fetchSize);
    List<T> findByExample(T exampleInstance, String[] excludeProperty);    
    T save(T entity);
    T update(T entity);
    void delete(T entity);       
}
