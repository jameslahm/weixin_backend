package com.tsinghua.course.Base.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("Discover")
public class Discover {

    @Id
    String id;

    String sender;

    String content;

    List<String> images;

    int timestamp;

    List<String> likesBy;

    List<Reply> replies;

    public Discover(String sender,String content,
                    List<String> images,int timestamp){
        this.sender = sender;
        this.content = content;
        this.images = images;
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

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
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

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getLikesBy() {
        return likesBy;
    }

    public void setLikesBy(List<String> likesBy) {
        this.likesBy = likesBy;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }
}
