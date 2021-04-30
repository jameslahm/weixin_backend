package com.tsinghua.course.Biz.Processor;

import com.tsinghua.course.Base.Model.Message;
import com.tsinghua.course.Base.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageProcessor {
    @Autowired
    MessageRepository messageRepository;

    public Message saveMessage(Message message){
        messageRepository.save(message);
        return message;
    }

    public Message createMessage(String content,String contentType,String messageType,
                                 long timestamp,String from,String to){
        Message message = new Message(content,contentType,messageType,timestamp,from,to);
        saveMessage(message);
        return message;
    }
}
