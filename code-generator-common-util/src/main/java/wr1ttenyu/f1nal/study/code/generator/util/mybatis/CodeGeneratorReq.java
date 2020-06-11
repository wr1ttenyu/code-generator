package wr1ttenyu.f1nal.study.code.generator.util.mybatis;

import java.util.List;

public class CodeGeneratorReq {

    private String connectionUrl;

    private String userId;

    private String password;

    private String javaModelPackage;

    private String sqlMapPackage;

    private String sqlXmlPackage;

    private List<TableConfigReq> tableConfigReqs;

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJavaModelPackage() {
        return javaModelPackage;
    }

    public void setJavaModelPackage(String javaModelPackage) {
        this.javaModelPackage = javaModelPackage;
    }

    public String getSqlMapPackage() {
        return sqlMapPackage;
    }

    public void setSqlMapPackage(String sqlMapPackage) {
        this.sqlMapPackage = sqlMapPackage;
    }

    public String getSqlXmlPackage() {
        return sqlXmlPackage;
    }

    public void setSqlXmlPackage(String sqlXmlPackage) {
        this.sqlXmlPackage = sqlXmlPackage;
    }

    public List<TableConfigReq> getTableConfigReqs() {
        return tableConfigReqs;
    }

    public void setTableConfigReqs(List<TableConfigReq> tableConfigReqs) {
        this.tableConfigReqs = tableConfigReqs;
    }

    @Override
    public String toString() {
        return "CodeGeneratorReq{" +
                "connectionUrl='" + connectionUrl + '\'' +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", javaModelPackage='" + javaModelPackage + '\'' +
                ", sqlMapPackage='" + sqlMapPackage + '\'' +
                ", sqlXmlPackage='" + sqlXmlPackage + '\'' +
                ", tableConfigReqs=" + tableConfigReqs +
                '}';
    }
}
