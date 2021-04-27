package com.tsinghua.course.Biz.Controller.Params.UserParams.Out;

import com.tsinghua.course.Base.Model.User;
import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;

public class ProfileOutParams extends CommonOutParams {

    String username;

    String id;

    String weixinId;

    public ProfileOutParams(User user){
        this.username = user.getUsername();
        this.id = user.getId();
        this.weixinId = user.getWeixinId();
    }

    public String getUsername() {
        return username;
    }
}
