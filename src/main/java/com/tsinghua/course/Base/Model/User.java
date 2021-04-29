package com.tsinghua.course.Base.Model;

import com.tsinghua.course.Base.Enum.UserType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述 对应mongodb中的User集合，mongodb是非关系型数据库，可以存储的对象类型很丰富，使用起来方便很多
 **/
@Document("User")
public class User {
    // mongodb唯一id
    @Id
    String id;

    // weixin ID
    @Indexed(unique = true)
    String weixinId;

    //Username
    String username;

    // Password
    String password;

    // userType
    UserType userType;

    // timeLineSyncId
    String timeLineSyncId;

    List<Friend> friends;

    String avatar = "http://139.196.81.14:7998/upload/avatar1.jpeg";

    public User(String weixinId,String username,String password,String timeLineSyncId){
        this.username = username;
        this.password = password;
        this.weixinId = weixinId;
        this.timeLineSyncId = timeLineSyncId;
        this.friends = new ArrayList<>();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getWeixinId() {
        return weixinId;
    }

    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
    }

    public String getTimeLineSyncId() {
        return timeLineSyncId;
    }

    public void setTimeLineSyncId(String timeLineSyncId) {
        this.timeLineSyncId = timeLineSyncId;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }
}
