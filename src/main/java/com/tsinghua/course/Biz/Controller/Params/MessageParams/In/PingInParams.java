package com.tsinghua.course.Biz.Controller.Params.MessageParams.In;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.Required;
import com.tsinghua.course.Base.Enum.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;

@BizType(BizTypeEnum.MESSAGE_PING)
public class PingInParams extends CommonInParams {

    @Required
    String ping;

    @Required
    String to;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getPing() {
        return ping;
    }

    public void setPing(String ping) {
        this.ping = ping;
    }
}
