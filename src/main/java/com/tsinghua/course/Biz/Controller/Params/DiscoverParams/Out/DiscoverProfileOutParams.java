package com.tsinghua.course.Biz.Controller.Params.DiscoverParams.Out;

import com.tsinghua.course.Base.Model.Discover;
import com.tsinghua.course.Base.Model.Reply;
import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;

import java.util.List;

public class DiscoverProfileOutParams extends CommonOutParams {
    String sender;
    String content;
    List<String> images;
    List<String> likesBy;
    List<Reply> replies;

    public DiscoverProfileOutParams(Discover discover) {
        this.sender = discover.getSender();
        this.content = discover.getContent();
        this.images = discover.getImages();
        this.likesBy = discover.getLikesBy();
        this.replies = discover.getReplies();
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
}
