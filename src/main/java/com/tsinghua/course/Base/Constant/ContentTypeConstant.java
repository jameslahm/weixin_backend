package com.tsinghua.course.Base.Constant;

public class ContentTypeConstant {
    public final static String TEXT = "TEXT";
    public final static String AUDIO = "AUDIO";
    public final static String VIDEO = "VIDEO";

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
