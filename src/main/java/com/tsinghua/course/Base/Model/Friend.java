package com.tsinghua.course.Base.Model;

public class Friend {
    String id;
    String timeLineSavedId;
    String nickName;

    public Friend(String id,String timelineId,String nickName){
        this.id = id;
        this.timeLineSavedId = timelineId;
        this.nickName = nickName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setTimeLineSavedId(String timeLineSavedId) {
        this.timeLineSavedId = timeLineSavedId;
    }

    public String getTimeLineSavedId() {
        return timeLineSavedId;
    }
}
