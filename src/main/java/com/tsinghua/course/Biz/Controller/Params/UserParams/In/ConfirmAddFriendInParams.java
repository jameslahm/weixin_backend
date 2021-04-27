package com.tsinghua.course.Biz.Controller.Params.UserParams.In;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.Required;
import com.tsinghua.course.Base.Enum.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;

@BizType(BizTypeEnum.USER_CONFIRM_ADD_FRIEND)
public class ConfirmAddFriendInParams extends CommonInParams {
    @Required
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
