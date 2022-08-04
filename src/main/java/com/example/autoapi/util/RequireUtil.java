package com.example.autoapi.util;

import com.example.autoapi.exception.RequireException;
import com.google.common.base.Strings;

public class RequireUtil {
    public static String requireNotNullOrEmpty(String str,String msg){
        if(Strings.isNullOrEmpty(str)){
            throw new RequireException(msg);
        }
        return str;

    }
}
