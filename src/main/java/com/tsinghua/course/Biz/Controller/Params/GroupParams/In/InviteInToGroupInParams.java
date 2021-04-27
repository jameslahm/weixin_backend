package com.tsinghua.course.Biz.Controller.Params.GroupParams.In;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.Required;
import com.tsinghua.course.Base.Enum.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;

import javax.xml.ws.RespectBinding;

@BizType(BizTypeEnum.GROUP_INVITE)
public class InviteInToGroupInParams extends CommonInParams {
    @Required
    String groupId;

    @Required
    String weixinId;

    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
    }

    public String getWeixinId() {
        return weixinId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
