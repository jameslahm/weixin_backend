package com.tsinghua.course.Biz.Controller.Params;

import com.tsinghua.course.Base.Enum.BizTypeEnum;

/**
 * @描述 通用入参，所有的入参都需要继承自此类
 **/
public class CommonInParams extends CommonParams {
    /** 发起请求的用户，如果已登录，系统会结合HttpSession或WebSocket长连接自动填充 */
    protected String id;

    /** 业务类型，服务器根据此变量来执行不同的业务 */
    protected BizTypeEnum bizType;

    public BizTypeEnum getBizType() {
        return bizType;
    }

    public void setBizType(BizTypeEnum bizType) {
        this.bizType = bizType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    protected void beforeTransfer() {}
}
