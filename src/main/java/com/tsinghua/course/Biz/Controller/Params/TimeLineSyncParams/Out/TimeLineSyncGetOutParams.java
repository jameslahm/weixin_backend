package com.tsinghua.course.Biz.Controller.Params.TimeLineSyncParams.Out;

import com.tsinghua.course.Base.Model.Message;
import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;

import java.util.List;

public class TimeLineSyncGetOutParams extends CommonOutParams {
    List<Message> messages;

    public TimeLineSyncGetOutParams(List<Message> messages){
        this.messages = messages;
    }
}
