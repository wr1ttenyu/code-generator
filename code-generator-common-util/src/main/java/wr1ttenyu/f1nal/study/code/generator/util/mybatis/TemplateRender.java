package wr1ttenyu.f1nal.study.code.generator.util.mybatis;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TemplateRender {

    public static String renderTemplate(String parentConfigContent, String tableConfigContent,
                                      CodeGeneratorReq codeGeneratorReq) {

        List<TableConfigReq> tableConfigReqs = codeGeneratorReq.getTableConfigReqs();
        String tableConfig = tableConfigReqs.stream().collect(ArrayList<String>::new, (list, item) ->
                        list.add(tableConfigContent.replace("${tableName}", item.getTableName())
                                .replace("${domainName}", item.getDomainName()))
                , (left, right) -> left.addAll(right)).stream().reduce("", (left, right) -> left + right);


        String parentConfig = replace(parentConfigContent, "${connectionURL}", codeGeneratorReq.getConnectionUrl());
        parentConfig = replace(parentConfig, "${userId}", codeGeneratorReq.getUserId());
        parentConfig = replace(parentConfig, "${password}", codeGeneratorReq.getPassword());
        parentConfig = replace(parentConfig, "${javaModelPackage}", codeGeneratorReq.getJavaModelPackage());
        parentConfig = replace(parentConfig, "${sqlMapPackage}", codeGeneratorReq.getSqlMapPackage());
        parentConfig = replace(parentConfig, "${sqlXmlPackage}", codeGeneratorReq.getSqlXmlPackage());
        parentConfig = replace(parentConfig, "${tables}", tableConfig);

        return parentConfig;
    }


    private static String replace(String source, String target, String content) {
        if (StringUtils.isEmpty(source) || StringUtils.isEmpty(target)) return "";
        return source.replace(target, content);
    }

    public static void main(String[] args) {
        CodeGeneratorReq req = new CodeGeneratorReq();
        req.setConnectionUrl("jdbc:mysql://122.51.219.124:3306/wr1ttenyu");
        req.setUserId("root");
        req.setPassword("p@ssword");

        req.setJavaModelPackage("wr1ttenyu.code.generator.test.dal.pojo");
        req.setSqlMapPackage("wr1ttenyu.code.generator.test.dal.mapper");
        req.setSqlXmlPackage("wr1ttenyu.code.generator.test.dal.mapper");

        TableConfigReq tableConfigReq = new TableConfigReq();
        tableConfigReq.setDomainName("UUser");
        tableConfigReq.setTableName("u_user");
        List<TableConfigReq> tableConfigReqs = Arrays.asList(tableConfigReq);
        req.setTableConfigReqs(tableConfigReqs);

        String s = JSONObject.toJSONString(req);
        System.out.println(s);
    }
}
