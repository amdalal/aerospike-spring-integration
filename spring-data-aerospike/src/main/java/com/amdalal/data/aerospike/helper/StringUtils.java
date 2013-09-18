package com.amdalal.data.aerospike.helper;

import java.util.Arrays;
import java.util.List;

public abstract class StringUtils {
    
    public static List<String> split(String input, String regex) {
        return Arrays.asList(input.split(regex));
    }
    
    public static boolean isEmpty(String str) {
        if (str == null)
            return true;
        return "".equals(str.trim());
    }
}
