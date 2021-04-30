package com.tsinghua.course.Base.Constant;

public class MessageTypeConstant {
    public final static String SINGLE = "SINGLE";
    public final static String GROUP = "GROUP";
    public final static String APPLICATION = "APPLICATION";
    public final static String INVITE = "INVITE";
    public final static String CONFIRM = "CONFIRM";

    static public boolean checkMessageTypeValid(String messageType){
        switch (messageType){
            case SINGLE:
            case GROUP:
            case APPLICATION:
            case INVITE:
            case CONFIRM:
                return true;
            default:
                return false;
        }
    }
}
