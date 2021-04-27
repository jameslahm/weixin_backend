package com.tsinghua.course.Base.Model;

import org.bson.BsonTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Message")
public class Message {

    enum ContentType {
        TEXT,
        AUDIO,
        VIDEO
    }

    enum MessageType {
        SINGLE,
        GROUP,
        APPLICATION
    }

    @Id
    String id;

    String content;

    ContentType contentType;

    MessageType messageType;

    BsonTimestamp timestamp;

    String from;

    String to;

    public Message(String content,ContentType contentType,
                   MessageType messageType,BsonTimestamp timestamp,
                   String from,String to){
        this.content = content;
        this.contentType = contentType;
        this.messageType = messageType;
        this.timestamp = timestamp;
        this.from = from;
        this.to = to;
    }
}
