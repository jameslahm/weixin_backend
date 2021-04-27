package com.tsinghua.course.Base.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("TimeLineSync")
public class TimeLineSync {

    @Id
    String id;

    @DBRef
    List<Message> messages;

    public TimeLineSync(){
        this.messages = new ArrayList<>();
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
