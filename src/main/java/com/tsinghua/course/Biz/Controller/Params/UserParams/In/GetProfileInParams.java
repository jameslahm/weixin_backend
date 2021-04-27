package com.tsinghua.course.Biz.Controller.Params.UserParams.In;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.Required;
import com.tsinghua.course.Base.Enum.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;

@BizType(BizTypeEnum.USER_GET_PROFILE)
public class GetProfileInParams extends CommonInParams {
    @Required
    private String weixinId;

    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
    }

    public String getWeixinId() {
        return weixinId;
    }
}
