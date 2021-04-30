package com.tsinghua.course.Biz.Controller.Params.MessageParams.Out;

import com.tsinghua.course.Biz.Controller.Params.CommonOutParams;

public class PingOutParams extends CommonOutParams {
    String ping;

    public String getPing() {
        return ping;
    }

    public void setPing(String ping) {
        this.ping = ping;
    }

    public PingOutParams(String ping) {
        this.ping = ping;
    }
}