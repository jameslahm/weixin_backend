package com.tsinghua.course.Biz.Controller;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.NeedLogin;
import com.tsinghua.course.Base.Enum.BizTypeEnum;
import com.tsinghua.course.Base.Model.Message;
import com.tsinghua.course.Base.Model.TimeLineSync;
import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;
import com.tsinghua.course.Biz.Controller.Params.TimeLineSyncParams.In.TimeLineSyncGetInParams;
import com.tsinghua.course.Biz.Controller.Params.TimeLineSyncParams.Out.TimeLineSyncGetOutParams;
import com.tsinghua.course.Biz.Processor.TimeLineSyncProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TimeLineSyncController {

    @Autowired
    TimeLineSyncProcessor timeLineSyncProcessor;
    
    @NeedLogin
    @BizType(BizTypeEnum.TIMELINESYNC_GET)
    public CommonOutParams meLineSyncGet(TimeLineSyncGetInParams inParams){
        String timeLineId = inParams.getTimeLineSyncId();
        int timestamp = inParams.getTimestamp();
        TimeLineSync timeLineSync =  timeLineSyncProcessor.getTimeLineSyncById(timeLineId);

        List<Message> messages = timeLineSyncProcessor.getMessagesAfterTimestamp(timeLineSync,timestamp);

        return new TimeLineSyncGetOutParams(messages);
    }

}
