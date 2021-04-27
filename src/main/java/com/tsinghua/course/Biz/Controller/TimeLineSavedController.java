package com.tsinghua.course.Biz.Controller;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.NeedLogin;
import com.tsinghua.course.Base.Enum.BizTypeEnum;
import com.tsinghua.course.Base.Error.CourseWarn;
import com.tsinghua.course.Base.Error.UserWarnEnum;
import com.tsinghua.course.Base.Model.Message;
import com.tsinghua.course.Base.Model.TimeLineSaved;
import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;
import com.tsinghua.course.Biz.Controller.Params.TimeLineSavedParams.In.TimeLineSavedGetInParams;
import com.tsinghua.course.Biz.Controller.Params.TimeLineSavedParams.Out.TimeLineSavedGetOutParams;
import com.tsinghua.course.Biz.Processor.TimeLineSavedProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TimeLineSavedController {
    @Autowired
    TimeLineSavedProcessor timeLineSavedProcessor;

    @NeedLogin
    @BizType(BizTypeEnum.TIMELINESAVED_GET)
    public CommonOutParams timeLineSavedGet(TimeLineSavedGetInParams inParams) throws Exception {
        String timeLineId = inParams.getTimeLineSavedId();
        TimeLineSaved timeLineSaved = timeLineSavedProcessor.getTimeLineSavedById(timeLineId);
        if(timeLineSaved==null){
            throw new CourseWarn(UserWarnEnum.NOT_FOUND);
        }

        List<Message> messages = timeLineSaved.getMessages();
        return new TimeLineSavedGetOutParams(messages);
    }

}
