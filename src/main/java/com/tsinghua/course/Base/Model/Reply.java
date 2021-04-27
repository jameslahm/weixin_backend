package com.tsinghua.course.Base.Model;

public class Reply {

    String friendId;

    String content;

    int timestamp;

    public Reply(String friendId,String content,int timestamp){
        this.friendId = friendId;
        this.content = content;
        this.timestamp = timestamp;
    }
}
