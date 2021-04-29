package com.tsinghua.course.Base.Model;

public class Friend {
    String id;
    String timeLineSavedId;
    String nickName;

    public Friend(String id,String timeLineSavedId,String nickName){
        this.id = id;
        this.timeLineSavedId = timeLineSavedId;
        this.nickName = nickName;
    }

    public static class FriendDetail {
        Friend friend;
        User user;

        public FriendDetail(Friend friend, User user) {
            this.friend = friend;
            this.user = user;
        }

        public Friend getFriend() {
            return friend;
        }

        public void setFriend(Friend friend) {
            this.friend = friend;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setTimeLineSavedId(String timeLineSavedId) {
        this.timeLineSavedId = timeLineSavedId;
    }

    public String getTimeLineSavedId() {
        return timeLineSavedId;
    }
}
