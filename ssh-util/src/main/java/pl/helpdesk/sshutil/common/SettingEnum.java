/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.sshutil.common;

import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * Load settings from properties file
 *
 * @author Mateusz Lubański <mlubanskii@gmail.com>
 */
public enum SettingEnum {
    
    PRIVATE_KEY_PATH("privateKeyPath"),
    UPGRADES_BACKUP_PATH("upgradesBackupPath");
    
    public static final Logger logger = Logger.getLogger(SettingEnum.class);
    private static final String SETTING_FILE = "setting.properties";
    private String key;
    
    
    private SettingEnum(String key) {
        this.key = key;
    }
    
    public String getValue() {
        
        Properties props = new Properties();
        try {
            props.load(ClassLoader.getSystemResourceAsStream(SETTING_FILE));
        } catch (IOException ex) {
            logger.error("Can't find setting file: ", ex);
        }
         
        return props.getProperty(this.key);
    }
}
