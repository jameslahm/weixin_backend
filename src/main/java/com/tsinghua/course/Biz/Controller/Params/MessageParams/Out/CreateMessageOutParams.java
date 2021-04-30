package com.tsinghua.course.Biz.Controller.Params.MessageParams.Out;

import com.tsinghua.course.Base.Model.Message;
import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;

public class CreateMessageOutParams extends CommonOutParams {
    String content;
    String contentType;
    String messageType;
    String to;
    String from;
    long timestamp;

    public CreateMessageOutParams(Message message){
        this.content = message.getContent();
        this.contentType = message.getContentType();
        this.messageType = message.getMessageType();
        this.from = message.getFrom();
        this.to = message.getTo();
        this.timestamp = message.getTimestamp();
    }
}
