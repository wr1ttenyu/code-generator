package wr1ttenyu.f1nal.study.code.generator.project.archetype.model;

import com.alibaba.fastjson.annotation.JSONField;
import wr1ttenyu.f1nal.study.code.generator.entity.UUser;
import wr1ttenyu.f1nal.study.code.generator.project.archetype.model.request.AddUserRequest;
import wr1ttenyu.f1nal.study.code.generator.util.UUIDGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserModel {

    private String id;

    private String name;

    private Integer age;

    @JSONField(format = "yyyy-MM-dd")
    private LocalDate birthday;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    public static UUser convertModelToDo(UserModel model) {
        if (model == null) return null;
        UUser user = new UUser();
        user.setId(model.getId());
        user.setAge(model.getAge());
        user.setName(model.getName());
        user.setBirthday(model.getBirthday());
        user.setCreateTime(model.getCreateTime());
        return user;
    }

    public static UserModel convertDoToModel(UUser entity) {
        if (entity == null) return null;
        UserModel user = new UserModel();
        user.setId(entity.getId());
        user.setAge(entity.getAge());
        user.setName(entity.getName());
        user.setBirthday(entity.getBirthday());
        user.setCreateTime(entity.getCreateTime());
        return user;
    }

    public static UserModel convertReqToModel(AddUserRequest request) {
        if (request == null) return null;
        UserModel user = new UserModel();
        user.setId(UUIDGenerator.generate());
        user.setAge(request.getAge());
        user.setName(request.getName());
        user.setBirthday(request.getBirthday());
        user.setCreateTime(LocalDateTime.now());
        return user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", createTime=" + createTime +
                '}';
    }
}
