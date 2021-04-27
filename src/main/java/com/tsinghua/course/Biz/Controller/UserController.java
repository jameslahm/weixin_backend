package com.tsinghua.course.Biz.Controller;

import com.tsinghua.course.Base.Annotation.BizType;
import com.tsinghua.course.Base.Annotation.NeedLogin;
import com.tsinghua.course.Base.Enum.BizTypeEnum;
import com.tsinghua.course.Base.Error.CourseWarn;
import com.tsinghua.course.Base.Error.UserWarnEnum;
import com.tsinghua.course.Base.Model.User;
import com.tsinghua.course.Biz.Controller.Params.CommonInParams;
import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;
import com.tsinghua.course.Biz.Controller.Params.UserParams.In.*;
import com.tsinghua.course.Biz.Controller.Params.UserParams.Out.ProfileOutParams;
import com.tsinghua.course.Biz.Processor.UserProcessor;
import com.tsinghua.course.Frame.Util.*;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @描述 用户控制器，用于执行用户相关的业务
 **/
@Component
public class UserController {

    @Autowired
    UserProcessor userProcessor;

    /** 用户登录业务 */
    @BizType(BizTypeEnum.USER_LOGIN)
    public CommonOutParams userSignIn(LoginInParams inParams) throws Exception {
        String weixinId = inParams.getWeixinId();
        User user = userProcessor.getUserByWeixinId(weixinId);
        if (user == null || !user.getPassword().equals(inParams.getPassword()))
            throw new CourseWarn(UserWarnEnum.LOGIN_FAILED);

        /** 登录成功，记录登录状态 */
        ChannelHandlerContext ctx =  ThreadUtil.getCtx();
        /** ctx不为空记录WebSocket状态，否则记录http状态 */
        if (ctx != null)
            SocketUtil.setUserSocket(user.getId(), ctx);
        else {
            HttpSession httpSession = ThreadUtil.getHttpSession();
            if (httpSession != null) {
                httpSession.setId(user.getId());
            }
        }
        return new CommonOutParams(true);
    }

    @BizType(BizTypeEnum.USER_REGISTER)
    public CommonOutParams userRegister(RegisterInParams inParams) throws Exception {
        String username = inParams.getUsername();
        String password = inParams.getPassword();
        String weixinId = inParams.getWeixinId();

        User user = userProcessor.getUserByWeixinId(weixinId);
        if(user!=null){
            throw new CourseWarn(UserWarnEnum.REGISTER_FAILED);
        }

        user = userProcessor.createUser(username,password,weixinId);

        return new ProfileOutParams(user);
    }

    @NeedLogin
    @BizType(BizTypeEnum.USER_PUT_PROFILE)
    public CommonOutParams userPutProfile(UpdateProfileInParams inParams) throws Exception {
        String weixinId = inParams.getWeixinId();
        String username = inParams.getUsername();
        String password = inParams.getPassword();

        User user = userProcessor.getUserByWeixinId(weixinId);
        if(user==null){
            throw new CourseWarn(UserWarnEnum.BAD_REQUEST);
        }

        user= ThreadUtil.getUser();
        userProcessor.updateUser(user,weixinId,username,password);

        return new CommonOutParams(true);
    }

    @BizType(BizTypeEnum.USER_GET_PROFILE)
    public CommonOutParams userGetProfile(GetProfileInParams inParams) throws Exception {
        String targetWeixinId = inParams.getWeixinId();
        User target = userProcessor.getUserByWeixinId(targetWeixinId);
        if(target==null){
            throw new CourseWarn(UserWarnEnum.NOT_FOUND);
        }
        return new ProfileOutParams(target);
    }

    @NeedLogin
    @BizType(BizTypeEnum.USER_LOGOUT)
    public CommonOutParams userLogOut(CommonInParams inParams) throws Exception {
        // only remove id in ctx
        // android should remove cookie
        ChannelHandlerContext ctx = ThreadUtil.getCtx();
        if(ctx!=null){
            SocketUtil.removeSocket(ctx);
        }
        return new CommonOutParams(true);
    }

    @NeedLogin
    @BizType(BizTypeEnum.USER_CONFIRM_ADD_FRIEND)
    public CommonOutParams userConfirmAddFriend(ConfirmAddFriendInParams inParams) throws Exception {
        String friendId = inParams.getWeixinId();
        User friend = userProcessor.getUserByWeixinId(friendId);
        if(friend==null) {
            throw new CourseWarn(UserWarnEnum.NOT_FOUND);
        }
        User user = ThreadUtil.getUser();

        userProcessor.addFriend(user,friend);
//        List friendList = Arrays.asList(friends);
//        friendList.add(new Friend(friendId,user.getTimeLineSyncId(),user.getUsername()));
//        friendList.toArray(friends);
        return new CommonOutParams(true);
    }
}
