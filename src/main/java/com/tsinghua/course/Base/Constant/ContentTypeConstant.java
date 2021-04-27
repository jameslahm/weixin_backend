package com.tsinghua.course.Base.Constant;

public class ContentTypeConstant {
    final static String TEXT = "TEXT";
    final static String AUDIO = "AUDIO";
    final static String VIDEO = "VIDEO";

    static public boolean checkContentTypeValid(String contentType){
        switch (contentType){
            case TEXT:
            case AUDIO:
            case VIDEO:
                return true;
            default:
                return false;
        }
    }
}
