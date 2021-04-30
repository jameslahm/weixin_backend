package com.tsinghua.course.Biz.Controller.Params.MessageParams.Out;

import com.tsinghua.course.Base.Model.Message;
import com.tsinghua.course.Base.Model.User;

public class CreateApplicationMessageOutParams extends CreateMessageOutParams {
    User user;

    public CreateApplicationMessageOutParams(Message message, User user) {
        super(message);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
