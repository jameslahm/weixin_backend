package com.tsinghua.course.Biz.Controller;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.NeedLogin;
import com.tsinghua.course.Base.Constant.ContentTypeConstant;
import com.tsinghua.course.Base.Constant.MessageTypeConstant;
import com.tsinghua.course.Base.Enum.BizTypeEnum;
import com.tsinghua.course.Base.Error.CourseWarn;
import com.tsinghua.course.Base.Error.UserWarnEnum;
import com.tsinghua.course.Base.Model.Group;
import com.tsinghua.course.Base.Model.Message;
import com.tsinghua.course.Base.Model.User;
import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;
import com.tsinghua.course.Biz.Controller.Params.GroupParams.In.CreateGroupInParams;
import com.tsinghua.course.Biz.Controller.Params.GroupParams.In.ExitGroupInParams;
import com.tsinghua.course.Biz.Controller.Params.GroupParams.In.InviteInToGroupInParams;
import com.tsinghua.course.Biz.Controller.Params.GroupParams.Out.GroupProfileOutParams;
import com.tsinghua.course.Biz.Controller.Params.MessageParams.Out.CreateInviteInToGroupOutParams;
import com.tsinghua.course.Biz.Processor.GroupProcessor;
import com.tsinghua.course.Biz.Processor.MessageProcessor;
import com.tsinghua.course.Biz.Processor.UserProcessor;
import com.tsinghua.course.Frame.Util.SocketUtil;
import com.tsinghua.course.Frame.Util.ThreadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupController {
    @Autowired
    GroupProcessor groupProcessor;

    @Autowired
    UserProcessor userProcessor;

    @Autowired
    MessageProcessor messageProcessor;

    @NeedLogin
    @BizType(BizTypeEnum.GROUP_CREATE)
    public CommonOutParams groupCreate(CreateGroupInParams inParams) throws Exception{
        String name = inParams.getName();
        List<String> members = inParams.getMembers();

        List<User> users = userProcessor.getUsersByIds(members);
        if(users.size()!=members.size()){
            throw new CourseWarn(UserWarnEnum.BAD_REQUEST);
        }

        users.add(0,ThreadUtil.getUser());
        Group group =  groupProcessor.createGroup(name,users);
        users.remove(0);

        String content = "You have been invited into "+ group.getName();

        User user = ThreadUtil.getUser();
        for (User target: users){
            Message message = messageProcessor.createMessage(content, ContentTypeConstant.TEXT, MessageTypeConstant.INVITE,
                    System.currentTimeMillis(),user.getId(),target.getId());
            SocketUtil.sendMessageToUser(target.getId(),new CreateInviteInToGroupOutParams(
                    message,group
            ));
        }
        return new GroupProfileOutParams(group);

    }

    @NeedLogin
    @BizType(BizTypeEnum.GROUP_INVITE)
    public CommonOutParams groupInvite(InviteInToGroupInParams inParams) throws Exception {
        String groupId = inParams.getGroupId();
        List<String> friendIds = inParams.getFriendIds();

        Group group = groupProcessor.getGroupById(groupId);
        if(group==null){
            throw new CourseWarn(UserWarnEnum.NOT_FOUND);
        }

        User user = ThreadUtil.getUser();

        if(!groupProcessor.checkUserInGroup(user,group)){
            throw new CourseWarn(UserWarnEnum.BAD_REQUEST);
        }

        for (String friendId:friendIds){
            User target = userProcessor.getUserById(friendId);
            groupProcessor.addMemberIntoGroup(target,group);

            String content = "You have been invited into "+ group.getName();
            Message message = messageProcessor.createMessage(content, ContentTypeConstant.TEXT, MessageTypeConstant.INVITE,
                    System.currentTimeMillis(),user.getId(),target.getId());
            SocketUtil.sendMessageToUser(target.getId(),new CreateInviteInToGroupOutParams(message,group));

        }
        return new GroupProfileOutParams(group);
    }

    @NeedLogin
    @BizType(BizTypeEnum.GROUP_EXIT)
    public CommonOutParams groupExit(ExitGroupInParams inParams) throws Exception{
        String groupId = inParams.getGroupId();
        Group group = groupProcessor.getGroupById(groupId);
        if(group==null){
            throw new CourseWarn(UserWarnEnum.NOT_FOUND);
        }
        User user = ThreadUtil.getUser();
        groupProcessor.deleteMemberInGroup(user,group);
        return new CommonOutParams(true);
    }

}
