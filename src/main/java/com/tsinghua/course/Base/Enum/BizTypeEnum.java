package com.tsinghua.course.Base.Enum;

import com.tsinghua.course.Biz.Controller.*;
import io.netty.handler.codec.http.HttpMethod;

/**
 * @描述 业务类型枚举，所有的业务类型都需要枚举在此类中
 **/
public enum BizTypeEnum {
    /** 以下为用户业务类型 */
    USER_LOGIN(UserController.class, "/user/login", "User LogIn",HttpMethod.POST),
    USER_LOGOUT(UserController.class,"/user/logout","User Logout",HttpMethod.POST),
    USER_REGISTER(UserController.class,"/user/register","User SignUp",HttpMethod.POST),
    USER_GET_PROFILE(UserController.class,"/user","User Get Profile",HttpMethod.GET),
    USER_PUT_PROFILE(UserController.class,"/user","User Put Profile",HttpMethod.PUT),
    USER_CONFIRM_ADD_FRIEND(UserController.class,"/user/addfriend","User confirm add friend",HttpMethod.POST),

    // Group
    GROUP_CREATE(GroupController.class,"/group","Group Create",HttpMethod.POST),
    GROUP_INVITE(GroupController.class,"/group/invite","Group Invite",HttpMethod.POST),
    GROUP_EXIT(GroupController.class,"/group/exit","Group Exit",HttpMethod.POST),

    // Message
    TIMELINESYNC_GET(TimeLineSyncController.class,"/timelinesync","TimeLine Sync Get",HttpMethod.GET),
    TIMELINESAVED_GET(TimeLineSavedController.class,"/timelinesaved","TimeLine Saved t",HttpMethod.GET),
    MESSAGE_CREATE(MessageController.class,"/message","Message Create",HttpMethod.POST),

    // Discover
    DISCOVER_CREATE(DiscoverController.class,"/discover","Discover Create",HttpMethod.POST),
    DISCOVER_GET_BY_FRIENDS(DiscoverController.class,"/discover","Discover Get",HttpMethod.POST),
    DISCOVER_LIKE(DiscoverController.class,"/discover/like","Discover Like",HttpMethod.POST),
    DISCOVER_UNLIKE(DiscoverController.class,"/discover/unline","Discover Unlike",HttpMethod.POST),
    DISCOVER_REPLY(DiscoverController.class,"/discover/reply","Discover Reply",HttpMethod.POST),

    /** 定时任务业务测试 */
    LOG_TEST(TimerController.class, null, "定时日志测试",HttpMethod.GET),

    /** 测试业务，在书写正式代码时可以删除，在书写正式代码前先运行测试业务，如果测试业务无问题说明各模块正常 */
    LOGIN_TEST(TestController.class, "/test/loginPermission", "登录控制测试",HttpMethod.GET),
    ADMIN_TEST(TestController.class, "/test/adminPermission", "管理员权限控制测试",HttpMethod.GET),
    REDIS_TEST(TestController.class, "/test/redis", "redis缓存测试",HttpMethod.GET),
    TIMER_TEST(TestController.class, "/test/timer", "定时器测试",HttpMethod.GET),
    ERROR_TEST(TestController.class, "/test/error", "内部报错测试",HttpMethod.GET),
    FILE_UPLOAD_TEST(TestController.class, "/test/upload", "文件上传测试",HttpMethod.GET),
    FILE_DOWNLOAD_TEST(TestController.class, "/test/url", "获取文件下载的路径",HttpMethod.GET),
    MULTI_RETURN_TEST(TestController.class, "/test/multiParams", "返回多个参数的测试",HttpMethod.GET),
    MONGODB_TEST(TestController.class, "/test/mongodb", "mongodb数据库功能测试",HttpMethod.GET);

    BizTypeEnum(Class<?> controller, String httpPath, String description, HttpMethod httpMethod) {
        this.controller = controller;
        this.description = description;
        this.httpPath = httpPath;
        this.httpMethod = httpMethod;
    }

    /** 执行业务具体的类 */
    Class<?> controller;
    /** 业务对应的http请求路径 */
    String httpPath;
    /** 业务描述 */
    String description;

    HttpMethod httpMethod;
    public Class<?> getControllerClass() {
        return controller;
    }

    public String getDescription() {
        return description;
    }

    public String getHttpPath() {
        return httpPath;
    }

    public HttpMethod getHttpMethod() {
        return this.httpMethod;
    }

}
