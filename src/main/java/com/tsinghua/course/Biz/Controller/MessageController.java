package com.tsinghua.course.Biz.Controller;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.NeedLogin;
import com.tsinghua.course.Base.Constant.ContentTypeConstant;
import com.tsinghua.course.Base.Constant.MessageTypeConstant;
import com.tsinghua.course.Base.Enum.BizTypeEnum;
import com.tsinghua.course.Base.Error.CourseWarn;
import com.tsinghua.course.Base.Error.UserWarnEnum;
import com.tsinghua.course.Base.Model.Friend;
import com.tsinghua.course.Base.Model.Group;
import com.tsinghua.course.Base.Model.Message;
import com.tsinghua.course.Base.Model.User;
import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;
import com.tsinghua.course.Biz.Controller.Params.MessageParams.In.CreateMessageInParams;
import com.tsinghua.course.Biz.Controller.Params.MessageParams.Out.CreateMessageOutParams;
import com.tsinghua.course.Biz.Processor.*;
import com.tsinghua.course.Frame.Util.SocketUtil;
import com.tsinghua.course.Frame.Util.ThreadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageController {
    @Autowired
    MessageProcessor messageProcessor;

    @Autowired
    UserProcessor userProcessor;

    @Autowired
    TimeLineSyncProcessor timeLineSyncProcessor;

    @Autowired
    GroupProcessor groupProcessor;

    @Autowired
    TimeLineSavedProcessor timeLineSavedProcessor;

    @NeedLogin
    @BizType(BizTypeEnum.MESSAGE_CREATE)
    public CommonOutParams messageCreate(CreateMessageInParams inParams) throws Exception {
        String content = inParams.getContent();
        String contentType = inParams.getContentType();
        String messageType = inParams.getMessageType();
        String from = inParams.getId();
        String to = inParams.getTo();
        int timestamp = inParams.getTimestamp();

        if(!MessageTypeConstant.checkMessageTypeValid(messageType)){
            throw new CourseWarn(UserWarnEnum.BAD_REQUEST);
        }
        if(!ContentTypeConstant.checkContentTypeValid(contentType)){
            throw new CourseWarn(UserWarnEnum.BAD_REQUEST);
        }

        Message message = messageProcessor.createMessage(content,contentType,messageType,timestamp,from,to);
        CreateMessageOutParams outParams = new CreateMessageOutParams(message);

        switch (messageType) {
            case MessageTypeConstant.APPLICATION:
            case MessageTypeConstant.SINGLE:
            {
                User target = userProcessor.getUserById(to);
                if(target==null){
                    throw new CourseWarn(UserWarnEnum.BAD_REQUEST);
                }

                User user = ThreadUtil.getUser();
                Friend friend = user.getFriends().stream().filter((friendElm)->{
                    return friendElm.getId()==target.getId();
                }).findFirst().orElse(null);
                if(friend==null){
                    throw new CourseWarn(UserWarnEnum.BAD_REQUEST);
                }

                timeLineSavedProcessor.addMessageInToTimeLineSaved(friend.getTimelineId(),message);
                timeLineSyncProcessor.addMessageInToTimeLineSync(target.getTimeLineSyncId(),message);
                SocketUtil.sendMessageToUser(target.getId(),outParams);
                break;
            }
            case MessageTypeConstant.GROUP:
            {
                Group group = groupProcessor.getGroupById(to);
                if(group==null){
                    throw new CourseWarn(UserWarnEnum.BAD_REQUEST);
                }
                List<User> members = group.getMembers();
                List<String> timeLineSyncIds = members.stream().map((member)->{
                    return member.getTimeLineSyncId();
                }).collect(Collectors.toList());

                timeLineSyncProcessor.addMessageInToTimeLineSyncs(timeLineSyncIds,message);

                List<String> ids = members.stream().map((member)->{
                    return member.getId();
                }).collect(Collectors.toList());
                SocketUtil.sendMessageToUsers(ids,outParams);
                break;
            }
            default:{
                throw new Exception("Should not reach here");
            }
        }
        return outParams;
    }
}

