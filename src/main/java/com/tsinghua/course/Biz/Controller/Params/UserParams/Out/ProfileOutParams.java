package com.tsinghua.course.Biz.Controller.Params.UserParams.Out;

import com.tsinghua.course.Base.Model.User;
import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;

public class ProfileOutParams extends CommonOutParams {

    private String username;

    public ProfileOutParams(User user){
        this.username = user.getUsername();
    }

    public String getUsername() {
        return username;
    }
}
