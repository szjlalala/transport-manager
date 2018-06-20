/**
 * Project Name：phoenix-core
 * File Name：PropertiesUtil.java
 * @version 1.0
 * @since JDK 1.7
 */
package com.tms.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * Package Name:com.un.general.common.utils ClassName: PropertiesUtil
 * Description：
 */
public class PropertiesUtil {

    public static String readValue(String propertiesPath, String key) {
        Resource resource = new ClassPathResource(propertiesPath);
        Properties props;
        try {
            props = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return props.getProperty(key, null);
    }
}
