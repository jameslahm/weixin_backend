package com.tsinghua.course.Frame.Util;

import java.util.Arrays;

public class ValidateUtil {
    static public boolean checkNotEmpty(String... targets){
        boolean hasEmpty =  Arrays.stream(targets).anyMatch((target)->{
            if(target.isEmpty() || target==null){
                return true;
            }
            return false;
        });
        return !hasEmpty;
    }
}
