package com.tsinghua.course.Base.Model;

import com.tsinghua.course.Biz.Controller.Params.UserParams.Out.ProfileOutParams;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Reply {

    @DBRef
    User user;

    String content;

    long timestamp;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Reply(User user, String content, long timestamp){
        this.user = user;
        this.content = content;
        this.timestamp = timestamp;
    }

    public static class ReplyOutParams {
        ProfileOutParams user;

        String content;

        long timestamp;

        public ProfileOutParams getUser() {
            return user;
        }

        public void setUser(ProfileOutParams user) {
            this.user = user;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public ReplyOutParams(User user, String content, long timestamp){
            this.user = new ProfileOutParams(user);
            this.content = content;
            this.timestamp = timestamp;
        }

      public static ReplyOutParams fromReply(Reply reply){
            return new ReplyOutParams(reply.user,reply.content,reply.timestamp);
        }
    }
}
