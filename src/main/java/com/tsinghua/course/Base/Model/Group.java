package com.tsinghua.course.Base.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("Group")
public class Group {
    @Id
    String id;

    String name;

    String timeLineSyncId;

    String avatar = "http://139.196.81.14:7998/upload/avatar1.jpeg";

    @DBRef
    List<User> members;

    public Group(String name, List<User> members){
        this.name = name;
        this.members = members;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTimeLineSyncId() {
        return timeLineSyncId;
    }

    public void setTimeLineSyncId(String timeLineSyncId) {
        this.timeLineSyncId = timeLineSyncId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
}
