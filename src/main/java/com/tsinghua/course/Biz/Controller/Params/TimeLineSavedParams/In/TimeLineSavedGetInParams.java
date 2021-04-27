package com.tsinghua.course.Biz.Controller.Params.TimeLineSavedParams.In;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.Required;
import com.tsinghua.course.Base.Enum.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;

@BizType(BizTypeEnum.TIMELINESAVED_GET)
public class TimeLineSavedGetInParams extends CommonInParams {

    @Required
    String timeLineId;

    public void setTimeLineId(String timeLineId) {
        this.timeLineId = timeLineId;
    }

    public String getTimeLineId() {
        return timeLineId;
    }
}
