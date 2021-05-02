package com.tsinghua.course.Biz.Controller;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.NeedLogin;
import com.tsinghua.course.Base.Enum.BizTypeEnum;
import com.tsinghua.course.Base.Error.CourseWarn;
import com.tsinghua.course.Base.Error.UserWarnEnum;
import com.tsinghua.course.Base.Model.Discover;
import com.tsinghua.course.Base.Model.User;
import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;
import com.tsinghua.course.Biz.Controller.Params.DiscoverParams.In.*;
import com.tsinghua.course.Biz.Controller.Params.DiscoverParams.Out.DiscoverProfileOutParams;
import com.tsinghua.course.Biz.Controller.Params.DiscoverParams.Out.GetDiscoverByFriendsOutParams;
import com.tsinghua.course.Biz.Processor.DiscoverProcessor;
import com.tsinghua.course.Frame.Util.ThreadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DiscoverController {
    @Autowired
    DiscoverProcessor discoverProcessor;

    @NeedLogin
    @BizType(BizTypeEnum.DISCOVER_CREATE)
    public CommonOutParams discoverCreate(CreateDiscoverInParams inParams) throws Exception {
        String content = inParams.getContent();
        long timestamp = inParams.getTimestamp();
        List<String> images = inParams.getImages();
        String video = inParams.getVideo();

        String senderId = inParams.getId();
        Discover discover = discoverProcessor.createDiscover(ThreadUtil.getUser(), content, images,video, timestamp);

        return new DiscoverProfileOutParams(discover);
    }

    @NeedLogin
    @BizType(BizTypeEnum.DISCOVER_GET_BY_FRIENDS)
    public CommonOutParams discoverGetByFriends(GetDiscoverInParams inParams) throws Exception {
        User user = ThreadUtil.getUser();
        List<Discover> discovers = discoverProcessor.getDiscoversByFriends(user);

        return new GetDiscoverByFriendsOutParams(discovers);

    }

    @NeedLogin
    @BizType(BizTypeEnum.DISCOVER_LIKE)
    public CommonOutParams discoverLike(LikeDiscoverInParams inParams) throws Exception {
        String discoverId = inParams.getDiscoverId();

        Discover discover = discoverProcessor.getDiscoverById(discoverId);

        if(discover==null){
            throw new CourseWarn(UserWarnEnum.NOT_FOUND);
        }

        User user = ThreadUtil.getUser();
        discoverProcessor.likeDiscover(user,discover);
        return new DiscoverProfileOutParams(discover);
    }

    @NeedLogin
    @BizType(BizTypeEnum.DISCOVER_UNLIKE)
    public CommonOutParams discoverUnLike(UnlikeDiscoverInParams inParams) throws Exception {
        String discoverId = inParams.getDiscoverId();
        Discover discover = discoverProcessor.getDiscoverById(discoverId);
        if(discover==null){
            throw new CourseWarn(UserWarnEnum.NOT_FOUND);
        }

        User user = ThreadUtil.getUser();
        discoverProcessor.unLikeDiscover(user,discover);
        return new DiscoverProfileOutParams(discover);
    }

    @NeedLogin
    @BizType(BizTypeEnum.DISCOVER_REPLY)
    public CommonOutParams discoverReply(ReplyDiscoverInParams inParams) throws Exception{
        String content = inParams.getContent();
        long timestamp = inParams.getTimestamp();
        String discoverId = inParams.getDiscoverId();

        Discover discover = discoverProcessor.getDiscoverById(discoverId);
        if(discover==null){
            throw new CourseWarn(UserWarnEnum.NOT_FOUND);
        }

        User user = ThreadUtil.getUser();
        discoverProcessor.replyDiscover(user,discover,content,timestamp);
        return new DiscoverProfileOutParams(discover);
    }
}
