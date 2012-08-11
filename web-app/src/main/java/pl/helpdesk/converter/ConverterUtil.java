/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.converter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import pl.helpdesk.model.BaseEntity;

/**
 *
 * @author matik06
 */
public class ConverterUtil {
    
    private static final Logger logger = Logger.getLogger(ConverterUtil.class);
    private static final String DEFAULT_LABEL_PATH = "name";
    
    
    public static <T extends BaseEntity<Integer>> List<SelectItem> convertList(List<T> entity) {
        return convertList(entity, DEFAULT_LABEL_PATH);
    }
    
    public static <T extends BaseEntity<Integer>> List<SelectItem> convertList(List<T> entity, String path) {
        List<SelectItem> result = new ArrayList<>();
        
        for (T t : entity) {
            SelectItem item = convert(t, path);
            result.add(item);
        }
        
        return result;
    }
    
    public static <T extends BaseEntity<Integer>> SelectItem convert(T entity) {
        return convert(entity, DEFAULT_LABEL_PATH);
    }
    
    public static <T extends BaseEntity<Integer>> SelectItem convert(T entity, String path) {
        
        String label = "";
        
        try {
            label = (String)PropertyUtils.getProperty(entity, path);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            logger.error(ex.getMessage(), ex);
        }
        
        SelectItem result = new SelectItem(entity, label);
        return result;
    }
            
            
}
