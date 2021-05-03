package com.tsinghua.course.Biz.Controller.Params.TimeLineSavedParams.In;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.Required;
import com.tsinghua.course.Base.Enum.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;

@BizType(BizTypeEnum.TIMELINESAVED_GET_SINGLE)
public class TimeLineSavedGetSingleInParams extends CommonInParams {

    @Required
    String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
