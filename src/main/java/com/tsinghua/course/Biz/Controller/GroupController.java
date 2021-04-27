package com.tsinghua.course.Biz.Controller;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.NeedLogin;
import com.tsinghua.course.Base.Enum.BizTypeEnum;
import com.tsinghua.course.Base.Error.CourseWarn;
import com.tsinghua.course.Base.Error.UserWarnEnum;
import com.tsinghua.course.Base.Model.Group;
import com.tsinghua.course.Base.Model.User;
import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;
import com.tsinghua.course.Biz.Controller.Params.GroupParams.In.CreateGroupInParams;
import com.tsinghua.course.Biz.Controller.Params.GroupParams.In.ExitGroupInParams;
import com.tsinghua.course.Biz.Controller.Params.GroupParams.In.InviteInToGroupInParams;
import com.tsinghua.course.Biz.Controller.Params.GroupParams.Out.GroupProfileOutParams;
import com.tsinghua.course.Biz.Processor.GroupProcessor;
import com.tsinghua.course.Biz.Processor.UserProcessor;
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

    @NeedLogin
    @BizType(BizTypeEnum.GROUP_CREATE)
    public CommonOutParams groupCreate(CreateGroupInParams inParams) throws Exception{
        String name = inParams.getName();
        List<String> members = inParams.getMembers();

        List<User> users = userProcessor.getUsersByWeixinIds();
        if(users.size()!=members.size()){
            throw new CourseWarn(UserWarnEnum.BAD_REQUEST);
        }
        Group group =  groupProcessor.createGroup(name,users);
        return new GroupProfileOutParams(group);

    }

    @NeedLogin
    @BizType(BizTypeEnum.GROUP_INVITE)
    public CommonOutParams groupInvite(InviteInToGroupInParams inParams) throws Exception {
        String groupId = inParams.getGroupId();
        String weixinId = inParams.getWeixinId();

        Group group = groupProcessor.getGroupById(groupId);
        if(group==null){
            throw new CourseWarn(UserWarnEnum.NOT_FOUND);
        }

        User user = ThreadUtil.getUser();

        if(!groupProcessor.checkUserInGroup(user,group)){
            throw new CourseWarn(UserWarnEnum.BAD_REQUEST);
        }

        User target = userProcessor.getUserByWeixinId(weixinId);
        groupProcessor.addMemberIntoGroup(user,group);
        return new CommonOutParams(true);
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
