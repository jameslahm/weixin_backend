package com.tsinghua.course.Biz.Controller.Params.DiscoverParams.In;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.Required;
import com.tsinghua.course.Base.Enum.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;

@BizType(BizTypeEnum.DISCOVER_UNLIKE)
public class UnlikeDiscoverInParams extends CommonInParams {
    @Required
    String discoverId;

    public String getDiscoverId() {
        return discoverId;
    }

    public void setDiscoverId(String discoverId) {
        this.discoverId = discoverId;
    }
}
