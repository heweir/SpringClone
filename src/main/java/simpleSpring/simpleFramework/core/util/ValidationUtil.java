package org.simpleFramework.core.util;

import java.util.Collection;
import java.util.Map;

public class ValidationUtil {
    /**
     * Collection 是否为null或者size为0
     * @param obj Collection
     * @return 是否为空
     */
    public static boolean isEmpty(Collection<?> obj){
        return obj == null || obj.isEmpty();
    }

    /**
     * String是否为空
     * @param obj String
     * @return 是否为空
     */
    public static boolean isEmpty(String obj){
        return (obj == null || ".".equals(obj));
    }

    /**
     * Array是否为空
     * @param obj Array
     * @return 是否为空
     */
    public static boolean isEmpty(Object[] obj){
        return obj == null||obj.length == 0;
    }
    public static boolean isEmpty(Object obj){
        return obj == null;
    }
    public static boolean isEmpty(Class<?> clazz){
        return clazz == null;
    }
    /**
     * Map 是否为空
     * @param obj Map
     * @return 是否为空
     */
    public static boolean isEmpty(Map<?,?> obj){
        return obj == null || obj.isEmpty();
    }
}
