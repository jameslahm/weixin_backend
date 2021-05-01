package com.tsinghua.course.Biz.Controller.Params.UserParams.Out;

import com.tsinghua.course.Base.Model.Friend;
import com.tsinghua.course.Base.Model.User;
import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;

import java.util.List;

public class ProfileOutParams extends CommonOutParams {
    String username;

    String id;

    String avatar;

    String weixinId;

    List<Friend> friends;

    public ProfileOutParams(User user){
        this.username = user.getUsername();
        this.id = user.getId();
        this.weixinId = user.getWeixinId();
        this.avatar = user.getAvatar();
        this.friends = user.getFriends();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getWeixinId() {
        return weixinId;
    }

    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public String getUsername() {
        return username;
    }
}
