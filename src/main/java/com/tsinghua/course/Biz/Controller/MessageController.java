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
import com.tsinghua.course.Biz.Controller.Params.MessageParams.In.PingInParams;
import com.tsinghua.course.Biz.Controller.Params.MessageParams.Out.CreateApplicationMessageOutParams;
import com.tsinghua.course.Biz.Controller.Params.MessageParams.Out.CreateMessageOutParams;
import com.tsinghua.course.Biz.Controller.Params.MessageParams.Out.PingOutParams;
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

    @BizType(BizTypeEnum.MESSAGE_PING)
    public CommonOutParams messagePing(PingInParams inParams) throws Exception {
        User user = userProcessor.getUserById(inParams.getTo());
        if(user==null){
            throw new CourseWarn(UserWarnEnum.BAD_REQUEST);
        }
        SocketUtil.sendMessageToUser(user.getId(),new PingOutParams(inParams.getPing()));
        return new CommonOutParams(true);
    }

    @NeedLogin
    @BizType(BizTypeEnum.MESSAGE_CREATE)
    public CommonOutParams messageCreate(CreateMessageInParams inParams) throws Exception {
        String content = inParams.getContent();
        String contentType = inParams.getContentType();
        String messageType = inParams.getMessageType();
        String from = inParams.getId();
        String to = inParams.getTo();
        long timestamp = inParams.getTimestamp();

        if(!MessageTypeConstant.checkMessageTypeValid(messageType)){
            throw new CourseWarn(UserWarnEnum.BAD_REQUEST);
        }
        if(!ContentTypeConstant.checkContentTypeValid(contentType)){
            throw new CourseWarn(UserWarnEnum.BAD_REQUEST);
        }

        Message message = messageProcessor.createMessage(content,contentType,messageType,timestamp,from,to);

        switch (messageType) {
            case MessageTypeConstant.APPLICATION:
            {
                User target = userProcessor.getUserById(to);
                if(target==null){
                    throw new CourseWarn(UserWarnEnum.BAD_REQUEST);
                }

                User user = ThreadUtil.getUser();
                timeLineSyncProcessor.addMessageInToTimeLineSync(target.getTimeLineSyncId(), message);

                CreateApplicationMessageOutParams outParams = new CreateApplicationMessageOutParams(message,user);
                SocketUtil.sendMessageToUser(target.getId(),outParams);
                break;
            }
            case MessageTypeConstant.SINGLE:
            {
                User target = userProcessor.getUserById(to);
                if(target==null){
                    throw new CourseWarn(UserWarnEnum.BAD_REQUEST);
                }

                User user = ThreadUtil.getUser();
                Friend friend = user.getFriends().stream().filter((friendElm)-> friendElm.getId()==target.getId()).findFirst().orElse(null);
                if(friend==null){
                    throw new CourseWarn(UserWarnEnum.BAD_REQUEST);
                }

                timeLineSavedProcessor.addMessageInToTimeLineSaved(friend.getTimeLineSavedId(),message);
                timeLineSyncProcessor.addMessageInToTimeLineSync(target.getTimeLineSyncId(),message);

                CreateMessageOutParams outParams = new CreateMessageOutParams(message);
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
                List<String> timeLineSyncIds = members.stream().map((member)-> member.getTimeLineSyncId()).collect(Collectors.toList());

                timeLineSavedProcessor.addMessageInToTimeLineSaved(group.getTimeLineSavedId(),message);
                timeLineSyncProcessor.addMessageInToTimeLineSyncs(timeLineSyncIds,message);

                List<String> ids = members.stream().map((member)-> member.getId()).collect(Collectors.toList());

                CreateMessageOutParams outParams = new CreateMessageOutParams(message);
                SocketUtil.sendMessageToUsers(ids,outParams);
                break;
            }
            default:{

            }
        }
        return new CommonOutParams(true);
    }
}

