/**
 * Copyright 2000-2012 tslee Co., All Rights Reserved.
 *   http://www.trans-cosmos.co.kr
 *
 * This source code is digitised property of tslee Company Limited ("tslee") and
 * protected by copyright under the law of Republic of Korea and other foreign laws.
 * Reproduction and/or redistribution of the source code without written permission of
 * tslee is not allowed. Also, it is severely prohibited to register whole or specific
 * part of the source code to any sort of information retrieval system.
 */
package com.jti.event.common.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;

/**
 * BaseModel class
 * 
 * @author tslee
 * @since 2016. 3. 21
 *
 */
public abstract class BaseModel implements Serializable {

    public static int getNvlInt(Integer obj)
    {
        if(obj == null)
            return -999999;
        else
            return obj.intValue();
    }

    public static long getNvlLong(Long obj)
    {
        if(obj == null)
            return -999999L;
        else
            return obj.longValue();
    }

    public static int getNvlInt(Integer obj, int defaultValue)
    {
        if(obj == null)
            return defaultValue;
        else
            return obj.intValue();
    }

    public static long getNvlLong(Long obj, long defaultValue)
    {
        if(obj == null)
            return defaultValue;
        else
            return obj.longValue();
    }

    public static String getNvlString(String obj, String defaultValue)
    {
        if(obj == null || obj.length() <= 0)
            return defaultValue;
        else
            return obj;
    }

    public static long getNullValueLong()
    {
        return -999999L;
    }

    public static int getNullValueInt()
    {
        return -999999;
    }

    public static String arrayToString(long array[])
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < array.length; i++)
        {
            if(i > 0)
                stringBuilder.append(";");
            stringBuilder.append(String.valueOf(array[i]));
        }

        return stringBuilder.toString();
    }

    public String toString()
    {
        return getReflectionToString(this);
    }

    public String getReflectionToString(Object object)
    {
        @SuppressWarnings("rawtypes")
		Class clazz = object.getClass();
        Field fields[] = clazz.getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        Field arr$[] = fields;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            Field field = arr$[i$];
            field.setAccessible(true);
            sb.append(field.getName());
            sb.append(" = ");
            try
            {
                boolean needBLR = !field.getType().isPrimitive() && !field.getType().equals(String.class) && !field.getType().equals(Date.class) && !field.getType().equals(BigDecimal.class);
                if(needBLR)
                    sb.append("{ ");
                sb.append(field.get(object));
                if(needBLR)
                    sb.append(" }");
            }
            catch(IllegalArgumentException e)
            {
                sb.append("IllegalArgumentException occured!!");
                sb.append(e.toString());
            }
            catch(IllegalAccessException e)
            {
                sb.append("IllegalAccessException occured!!");
                sb.append(e.toString());
            }
            sb.append(";").append("\n");
        }

        return sb.toString();
    }

    private static final long serialVersionUID = 1L;
    public static final int nullValueInt = -999999;
    public static final long nullValueLong = -999999L;
}
