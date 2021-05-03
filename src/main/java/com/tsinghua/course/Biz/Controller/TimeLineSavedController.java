package com.tsinghua.course.Biz.Controller;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.NeedLogin;
import com.tsinghua.course.Base.Enum.BizTypeEnum;
import com.tsinghua.course.Base.Error.CourseWarn;
import com.tsinghua.course.Base.Error.UserWarnEnum;
import com.tsinghua.course.Base.Model.*;
import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;
import com.tsinghua.course.Biz.Controller.Params.TimeLineSavedParams.In.TimeLineSavedGetGroupInParams;
import com.tsinghua.course.Biz.Controller.Params.TimeLineSavedParams.In.TimeLineSavedGetSingleInParams;
import com.tsinghua.course.Biz.Controller.Params.TimeLineSavedParams.Out.TimeLineSavedGetOutParams;
import com.tsinghua.course.Biz.Processor.GroupProcessor;
import com.tsinghua.course.Biz.Processor.TimeLineSavedProcessor;
import com.tsinghua.course.Frame.Util.ThreadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TimeLineSavedController {
    @Autowired
    TimeLineSavedProcessor timeLineSavedProcessor;

    @Autowired
    GroupProcessor groupProcessor;

    @NeedLogin
    @BizType(BizTypeEnum.TIMELINESAVED_GET_SINGLE)
    public CommonOutParams timeLineSavedGetSingle(TimeLineSavedGetSingleInParams inParams) throws Exception {
        String friendId = inParams.getUserId();

        User user = ThreadUtil.getUser();

        Friend friend = user.getFriends().stream().filter((friend1 -> {
            return friend1.getId().equals(friendId);
        })).findFirst().orElse(null);

        if(friend==null){
            throw new CourseWarn(UserWarnEnum.BAD_REQUEST);
        }

        String timeLineId = friend.getTimeLineSavedId();

        TimeLineSaved timeLineSaved = timeLineSavedProcessor.getTimeLineSavedById(timeLineId);
        if(timeLineSaved==null){
            throw new CourseWarn(UserWarnEnum.NOT_FOUND);
        }

        List<Message> messages = timeLineSaved.getMessages();
        return new TimeLineSavedGetOutParams(messages);
    }

    @NeedLogin
    @BizType(BizTypeEnum.TIMELINESAVED_GET_GROUP)
    public CommonOutParams timeLineSavedGetGroup(TimeLineSavedGetGroupInParams inParams) throws Exception {
        String groupId = inParams.getGroupId();

        Group group = groupProcessor.getGroupById(groupId);

        if(group==null){
            throw new CourseWarn(UserWarnEnum.BAD_REQUEST);
        }
        String timeLineId = group.getTimeLineSavedId();

        TimeLineSaved timeLineSaved = timeLineSavedProcessor.getTimeLineSavedById(timeLineId);
        if(timeLineSaved==null){
            throw new CourseWarn(UserWarnEnum.NOT_FOUND);
        }

        List<Message> messages = timeLineSaved.getMessages();
        return new TimeLineSavedGetOutParams(messages);
    }

}
