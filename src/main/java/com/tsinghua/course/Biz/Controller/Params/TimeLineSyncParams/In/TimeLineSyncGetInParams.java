package com.tsinghua.course.Biz.Controller.Params.TimeLineSyncParams.In;

import com.tsinghua.course.Base.Annotation.Required;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;


//@BizType(BizTypeEnum.TIMELINESYNC_GET)
public class TimeLineSyncGetInParams extends CommonInParams {

    @Required
    String timeLineSyncId;

    @Required
    int timestamp;


    public void setTimeLineSyncId(String timeLineSyncId) {
        this.timeLineSyncId = timeLineSyncId;
    }

    public String getTimeLineSyncId() {
        return timeLineSyncId;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
