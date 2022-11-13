package com.api.apiresttest.api.util;

public class StringHelper {
    public static boolean isEmpty(String value){
        if(value == null || value.isEmpty()){
            return true;
        }
        return false;
    }
}
