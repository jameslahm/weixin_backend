package com.tsinghua.course.Biz.Controller.Params.DiscoverParams.Out;

import com.tsinghua.course.Base.Model.Discover;
import com.tsinghua.course.Base.Model.Reply;
import com.tsinghua.course.Base.Model.User;
import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;
import com.tsinghua.course.Biz.Controller.Params.UserParams.Out.ProfileOutParams;

import java.util.List;
import java.util.stream.Collectors;

public class DiscoverProfileOutParams extends CommonOutParams {

    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    User sender;
    String content;
    List<String> images;
    List<ProfileOutParams> likesBy;
    List<Reply.ReplyOutParams> replies;

    long timestamp;

    String video;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public DiscoverProfileOutParams(Discover discover) {
        this.sender = discover.getSender();
        this.content = discover.getContent();
        this.id = discover.getId();
        this.timestamp = discover.getTimestamp();
        this.images = discover.getImages();
        this.video = discover.getVideo();
        this.likesBy = discover.getLikesBy().stream().map(likesBy->{
            return new ProfileOutParams(likesBy);
        }).collect(Collectors.toList());
        this.replies = discover.getReplies().stream().map(reply -> {
            return Reply.ReplyOutParams.fromReply(reply);
        }).collect(Collectors.toList());
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public List<ProfileOutParams> getLikesBy() {
        return likesBy;
    }

    public void setLikesBy(List<ProfileOutParams> likesBy) {
        this.likesBy = likesBy;
    }

    public List<Reply.ReplyOutParams> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply.ReplyOutParams> replies) {
        this.replies = replies;
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
}
