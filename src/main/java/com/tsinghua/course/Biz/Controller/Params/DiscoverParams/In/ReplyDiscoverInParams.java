package com.tsinghua.course.Biz.Controller.Params.DiscoverParams.In;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.Required;
import com.tsinghua.course.Base.Enum.BizTypeEnum;

@BizType(BizTypeEnum.DISCOVER_REPLY)
public class ReplyDiscoverInParams {
    @Required
    String discoverId;

    @Required
    String content;

    @Required
    int timestamp;

    public String getDiscoverId() {
        return discoverId;
    }

    public void setDiscoverId(String discoverId) {
        this.discoverId = discoverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
