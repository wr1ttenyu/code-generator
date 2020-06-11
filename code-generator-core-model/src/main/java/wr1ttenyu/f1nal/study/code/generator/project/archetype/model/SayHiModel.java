package wr1ttenyu.f1nal.study.code.generator.project.archetype.model;

import com.alibaba.fastjson.annotation.JSONField;
import wr1ttenyu.f1nal.study.code.generator.entity.MSayHi;
import wr1ttenyu.f1nal.study.code.generator.project.archetype.model.request.SayHiRequest;
import wr1ttenyu.f1nal.study.code.generator.project.archetype.model.request.SayHiResponse;

import java.time.LocalDateTime;

public class SayHiModel {

    private String reqMsg;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reqTime;

    private String resMsg;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime resTime;

    public static MSayHi convertModelToDo(SayHiModel model) {
        if (model == null) return null;
        MSayHi entity = new MSayHi();
        entity.setReqMsg(model.getReqMsg());
        entity.setReqTime(model.getReqTime());
        entity.setResMsg(model.getResMsg());
        entity.setResTime(model.getResTime());
        return entity;
    }

    public static SayHiModel convertDoToModel(MSayHi entity) {
        if (entity == null) return null;
        SayHiModel model = new SayHiModel();
        model.setReqMsg(entity.getReqMsg());
        model.setReqTime(entity.getReqTime());
        model.setResMsg(entity.getResMsg());
        model.setResTime(entity.getResTime());
        return model;
    }

    public static SayHiModel convertReqToModel(SayHiRequest request) {
        if (request == null) return null;
        SayHiModel model = new SayHiModel();
        model.setReqMsg(request.getReqMsg());
        model.setReqTime(request.getReqTime());
        return model;
    }

    public static SayHiResponse convertModelToRes(SayHiModel model, UserModel userModel) {
        if (model == null) return null;
        SayHiResponse res = new SayHiResponse();
        res.setReqMsg(model.getReqMsg());
        res.setReqTime(model.getReqTime());
        res.setResMsg(model.getResMsg());
        res.setResTime(model.getResTime());
        return res;
    }

    public String getReqMsg() {
        return reqMsg;
    }

    public void setReqMsg(String reqMsg) {
        this.reqMsg = reqMsg;
    }

    public LocalDateTime getReqTime() {
        return reqTime;
    }

    public void setReqTime(LocalDateTime reqTime) {
        this.reqTime = reqTime;
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

    @Override
    public String toString() {
        return "SayHiModel{" +
                "reqMsg='" + reqMsg + '\'' +
                ", reqTime=" + reqTime +
                ", resMsg='" + resMsg + '\'' +
                ", resTime=" + resTime +
                '}';
    }
}
