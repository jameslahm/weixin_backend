package com.tsinghua.course.Base.Model;

import org.springframework.data.annotation.Id;

public class Friend {
    String id;
    String timelineId;
    String nickName;

    public Friend(String id,String timelineId,String nickName){
        this.id = id;
        this.timelineId = timelineId;
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

    public void setTimelineId(String timelineId) {
        this.timelineId = timelineId;
    }

    public String getTimelineId() {
        return timelineId;
    }
}
