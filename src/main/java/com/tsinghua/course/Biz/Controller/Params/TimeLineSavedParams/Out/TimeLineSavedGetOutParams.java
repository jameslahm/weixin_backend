package com.tsinghua.course.Biz.Controller.Params.TimeLineSavedParams.Out;

import com.tsinghua.course.Base.Model.Message;
import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;

import java.util.List;

public class TimeLineSavedGetOutParams  extends CommonOutParams {
    List<Message> messages;

    public TimeLineSavedGetOutParams(List<Message> messages){
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
