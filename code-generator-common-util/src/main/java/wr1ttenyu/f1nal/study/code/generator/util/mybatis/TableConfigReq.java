package wr1ttenyu.f1nal.study.code.generator.util.mybatis;

public class TableConfigReq {

    private String tableName;

    private String domainName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    @Override
    public String toString() {
        return "TableConfigReq{" +
                "tableName='" + tableName + '\'' +
                ", domainName='" + domainName + '\'' +
                '}';
    }
}
