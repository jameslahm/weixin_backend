package com.tsinghua.course.Biz.Controller.Params.GroupParams.In;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.Required;
import com.tsinghua.course.Base.Enum.BizTypeEnum;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;

import java.util.List;

@BizType(BizTypeEnum.GROUP_CREATE)
public class CreateGroupInParams extends CommonInParams {

    @Required
    private String name;

    @Required
    private List<String> members;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public List<String> getMembers() {
        return members;
    }
}
