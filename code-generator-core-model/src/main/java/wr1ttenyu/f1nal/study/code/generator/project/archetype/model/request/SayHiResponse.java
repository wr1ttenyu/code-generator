package wr1ttenyu.f1nal.study.code.generator.project.archetype.model.request;


import com.alibaba.fastjson.annotation.JSONField;
import wr1ttenyu.f1nal.study.code.generator.project.archetype.model.UserModel;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class SayHiResponse {

    @NotNull
    private String reqMsg;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reqTime;

    @NotNull
    private String resMsg;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime resTime;

    private UserModel user;

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getReqMsg() {
        return reqMsg;
    }

    public void setReqMsg(String reqMsg) {
        this.reqMsg = reqMsg;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public LocalDateTime getResTime() {
        return resTime;
    }

    public void setResTime(LocalDateTime resTime) {
        this.resTime = resTime;
    }

    public LocalDateTime getReqTime() {
        return reqTime;
    }

    public void setReqTime(LocalDateTime reqTime) {
        this.reqTime = reqTime;
    }

    @Override
    public String toString() {
        return "SayHiResponse{" +
                "reqMsg='" + reqMsg + '\'' +
                ", reqTime=" + reqTime +
                ", resMsg='" + resMsg + '\'' +
                ", resTime=" + resTime +
                ", user=" + user +
                '}';
    }
}
