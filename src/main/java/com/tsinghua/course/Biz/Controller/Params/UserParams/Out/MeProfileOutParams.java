package com.tsinghua.course.Biz.Controller.Params.UserParams.Out;

import com.tsinghua.course.Base.Model.Friend;
import com.tsinghua.course.Base.Model.User;
import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;

import java.util.List;

public class MeProfileOutParams extends CommonOutParams {
    String username;

    String id;

    String avatar;

    String weixinId;

    List<Friend.FriendDetail> friends;

    public MeProfileOutParams(User user, List<Friend.FriendDetail> friends){
        this.username = user.getUsername();
        this.id = user.getId();
        this.weixinId = user.getWeixinId();
        this.avatar = user.getAvatar();
        this.friends = friends;
    }

    public String getUsername() {
        return username;
    }
}
