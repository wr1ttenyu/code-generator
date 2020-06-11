package wr1ttenyu.f1nal.study.code.generator.project.archetype.model.request;


import com.alibaba.fastjson.annotation.JSONField;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AddUserRequest {

    @NotNull
    private String name;

    @NotNull
    @DecimalMin(value = "0")
    private Integer age;

    @JSONField(format = "yyyy-MM-dd")
    private LocalDate birthday;

    private LocalDateTime deadTime;

    private Boolean male;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getMale() {
        return male;
    }

    public void setMale(Boolean male) {
        this.male = male;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getDeadTime() {
        return deadTime;
    }

    public void setDeadTime(LocalDateTime deadTime) {
        this.deadTime = deadTime;
    }
}
