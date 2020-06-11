package wr1ttenyu.f1nal.study.code.generator.project.archetype.model.request;


import com.alibaba.fastjson.annotation.JSONField;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class SayHiRequest {

    @NotNull
    private String reqMsg;

    @NotNull
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reqTime;

    public LocalDateTime getReqTime() {
        return reqTime;
    }

    public void setReqTime(LocalDateTime reqTime) {
        this.reqTime = reqTime;
    }

    public String getReqMsg() {
        return reqMsg;
    }

    public void setReqMsg(String reqMsg) {
        this.reqMsg = reqMsg;
    }

    @Override
    public String toString() {
        return "SayHiRequest{" +
                "reqMsg='" + reqMsg + '\'' +
                ", reqTime=" + reqTime +
                '}';
    }
}
