package com.tsinghua.course.Frame.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @描述 http的session，用于记录http连接的信息
 **/
@Component
public class HttpSession {

    public static final Map<String, HttpSession> sessionMap = new ConcurrentHashMap<>();

    @Autowired
    private static RedisUtil redisUtil;

    /** session的id和对应的用户名 */
    private String sessionId;
    private String id;

    public HttpSession() {
        /** 初始化时随机生成session的id */
        sessionId = UUID.randomUUID().toString();
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
//        redisUtil.setKey(sessionId,id);
    }

    public static boolean sessionExist(String sessionId) {
        return sessionMap.containsKey(sessionId);
    }

    /** 新建session */
    public static HttpSession newSession() {
        HttpSession session = new HttpSession();
        String sessionId = session.getSessionId();
        sessionMap.put(sessionId, session);
//        String id =  redisUtil.getString(sessionId);
//        if(id!=null){
//            session.setId(id);
//        }
        return session;
    }

    public static HttpSession getSession(String sessionId) {
        return sessionMap.get(sessionId);
    }

    /** 移除session */
    public static void removeSession(String sessionId) {
        sessionMap.remove(sessionId);
//        redisUtil.deleteKeys(sessionId);
    }
}
