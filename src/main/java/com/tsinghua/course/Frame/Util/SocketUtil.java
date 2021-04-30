package com.tsinghua.course.Frame.Util;

import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @描述 维护WebSocket长连接和对应用户的组件，socket是长连接，多次请求的管道不会变，可以直接用管道当key
 **/
public class SocketUtil {
    /** 用户名到管道的map */
    private static ConcurrentHashMap<String, ChannelHandlerContext> socketMap = new ConcurrentHashMap<>();
    /** 管道到用户名的map */
    private static ConcurrentHashMap<ChannelHandlerContext, String> ctxToUser = new ConcurrentHashMap<>();

    /** 设置用户管道 */
    public static void setUserSocket(String id, ChannelHandlerContext ctx) {
        ChannelHandlerContext preCtx = socketMap.get(id);
        if (preCtx != null)
            ctxToUser.remove(preCtx);
        socketMap.put(id, ctx);
        ctxToUser.put(ctx, id);
    }
    /** 根据管道获取用户名 */
    public static String getSocketUser(ChannelHandlerContext ctx) {
        return ctxToUser.get(ctx);
    }
    /** 移除管道 */
    public static void removeSocket(ChannelHandlerContext ctx) {
        String id = ctxToUser.get(ctx);
        ctxToUser.remove(ctx);
        if (id != null) {
            socketMap.remove(id);
        }
    }
    /** 向单个用户发送单个信息 */
    public static void sendMessageToUser(String id, CommonOutParams outParams) throws Exception {
        sendMessageToUser(id, outParams.toString());
    }
    /** 向多个用户发送单个信息 */
    public static void sendMessageToUsers(Collection<String> ids, CommonOutParams msgs) throws Exception {
        for(String id:ids){
            sendMessageToUser(id,msgs);
        };
    }

    /** 向单个用户发送多个信息 */
    public static void sendMessageToUser(String id, String content) throws Exception {
        ChannelHandlerContext ctx = socketMap.get(id);
        if (ctx != null && ctx.channel().isActive()) {
            LogUtil.INFO("Send WebSocket ",content);
            ctx.channel().writeAndFlush(new TextWebSocketFrame(content));
        }
    }
}
