/**
* Project Name：phoenix-core
* File Name：MapUtils.java
* Date：2016年8月29日 上午10:09:54
* Description：
* @author YuanWenLong
* @version 1.0
* @since JDK 1.7
*/
package com.tms.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Package Name:com.ylpw.core.util ClassName: MapUtils Description：
 */
public class MapUtils {
    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }

        Field[] declaredFieldsParent = obj.getClass().getSuperclass().getDeclaredFields();
        for (Field field : declaredFieldsParent) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }

        return map;
    }
}
