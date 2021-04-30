package com.tsinghua.course.Biz.Controller.Params.MessageParams.Out;


import com.tsinghua.course.Base.Model.Group;
import com.tsinghua.course.Base.Model.Message;

public class CreateInviteInToGroupOutParams extends CreateMessageOutParams {
    Group group;

    public CreateInviteInToGroupOutParams(Message message,Group group){
        super(message);
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
