package com.tsinghua.course.Base.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("Discover")
public class Discover {

    @Id
    String id;

    @DBRef
    User sender;

    String content;

    List<String> images;

    long timestamp;

    String video;

    @DBRef
    List<User> likesBy;

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    List<Reply> replies;

    public Discover(User sender,String content,
                    List<String> images,String video,long timestamp){
        this.sender = sender;
        this.content = content;
        this.images = images;
        this.video = video;
        this.timestamp = timestamp;
        this.likesBy = new ArrayList<>();
        this.replies = new ArrayList<>();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<User> getLikesBy() {
        return likesBy;
    }

    public void setLikesBy(List<User> likesBy) {
        this.likesBy = likesBy;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }
}
