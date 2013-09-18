package com.amdalal.data.aerospike.helper;

import java.lang.reflect.Field;

import org.apache.commons.lang.WordUtils;

public class SetterGetterHelper {

    public static String getSetterName(Field f) {
        return "set" + WordUtils.capitalize(f.getName());
    }

    public static String getGetterName(Field f) {
        String prefix = "get";
        if (f.getType().equals(Boolean.class) || f.getType().equals(boolean.class)) {
            prefix = "is";
        }
        return prefix + WordUtils.capitalize(f.getName());
    }
}
