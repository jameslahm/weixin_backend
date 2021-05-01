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

    String timeLineSavedId;

    String avatar = "http://139.196.81.14:7998/upload/avatar1.jpeg";

    @DBRef
    List<User> members;

    public Group(String name, String timeLineSavedId, List<User> members){
        this.timeLineSavedId = timeLineSavedId;
        this.name = name;
        this.members = members;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTimeLineSavedId() {
        return timeLineSavedId;
    }

    public void setTimeLineSavedId(String timeLineSavedId) {
        this.timeLineSavedId = timeLineSavedId;
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
