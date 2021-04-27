package com.tsinghua.course.Biz.Controller.Params.TimeLineSyncParams.In;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.Required;
import com.tsinghua.course.Base.Enum.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;


@BizType(BizTypeEnum.TIMELINESYNC_GET)
public class TimeLineSyncGetInParams extends CommonInParams {

    @Required
    String timeLineId;

    @Required
    int timestamp;


    public void setTimeLineId(String timeLineId) {
        this.timeLineId = timeLineId;
    }

    public String getTimeLineId() {
        return timeLineId;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
