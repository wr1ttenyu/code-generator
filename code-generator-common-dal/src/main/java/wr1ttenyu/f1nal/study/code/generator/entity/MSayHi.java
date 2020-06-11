package wr1ttenyu.f1nal.study.code.generator.entity;

import com.sun.istack.internal.NotNull;

import java.time.LocalDateTime;

public class MSayHi {

    private String reqMsg;

    private LocalDateTime reqTime;

    private String resMsg;

    private LocalDateTime resTime;

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
                '}';
    }
}