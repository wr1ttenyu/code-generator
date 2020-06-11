package wr1ttenyu.f1nal.study.code.generator.project.archetype.model;

public class TokenModel {

    private Long expire;

    private String token;

    private UserModel user;

    public TokenModel() {}

    public TokenModel(Long expire, String token, UserModel user) {
        this.expire = expire;
        this.token = token;
        this.user = user;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

}
