package com.tsinghua.course.Biz.Controller.Params.GroupParams.In;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.Required;
import com.tsinghua.course.Base.Enum.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;

import java.util.List;

@BizType(BizTypeEnum.GROUP_INVITE)
public class InviteInToGroupInParams extends CommonInParams {
    @Required
    String groupId;

    @Required
    List<String> friendIds;

    public List<String> getFriendIds() {
        return friendIds;
    }

    public void setFriendId(List<String> friendIds) {
        this.friendIds = friendIds;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
