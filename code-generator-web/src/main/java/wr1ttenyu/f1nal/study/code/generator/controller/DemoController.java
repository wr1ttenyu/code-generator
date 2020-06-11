package wr1ttenyu.f1nal.study.code.generator.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wr1ttenyu.f1nal.study.code.generator.util.mybatis.CodeGeneratorReq;
import wr1ttenyu.f1nal.study.code.generator.util.mybatis.TableConfigReq;
import wr1ttenyu.f1nal.study.code.generator.util.mybatis.TemplateRender;
import wr1ttenyu.f1nal.study.code.generator.project.archetype.model.SayHiModel;
import wr1ttenyu.f1nal.study.code.generator.project.archetype.model.TokenModel;
import wr1ttenyu.f1nal.study.code.generator.project.archetype.model.UserModel;
import wr1ttenyu.f1nal.study.code.generator.project.archetype.model.request.AddUserRequest;
import wr1ttenyu.f1nal.study.code.generator.project.archetype.model.request.SayHiRequest;
import wr1ttenyu.f1nal.study.code.generator.project.archetype.model.request.SayHiResponse;
import wr1ttenyu.f1nal.study.code.generator.service.DemoService;
import wr1ttenyu.f1nal.study.code.generator.util.common.response.CommonResponse;
import wr1ttenyu.f1nal.study.code.generator.util.mybatis.Wr1ShellRunner;
import wr1ttenyu.f1nal.study.code.generator.web.utils.token.TokenManager;
import wr1ttenyu.f1nal.study.code.generator.web.utils.token.TokenValid;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@Validated
@RestController
@RequestMapping("/hello")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping(method = RequestMethod.POST, path = "/getToken")
    public CommonResponse<String> getToken(@RequestBody AddUserRequest user) {
        TokenModel tokenModel = TokenManager.makeToken(UserModel.convertReqToModel(user));
        return CommonResponse.successResponse(tokenModel.getToken());
    }

    @TokenValid
    @RequestMapping(method = RequestMethod.POST, path = "/sayHi")
    public CommonResponse<SayHiResponse> getUserInfoById(@RequestBody SayHiRequest sayHiRequest, UserModel userModel) {
        SayHiModel sayHiModel = demoService.sayHi(SayHiModel.convertReqToModel(sayHiRequest), userModel);
        return CommonResponse.successResponse(SayHiModel.convertModelToRes(sayHiModel, userModel));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/generator")
    public ResponseEntity<byte[]> getToken(/*@RequestBody CodeGeneratorReq codeGeneratorReq*/) throws IOException {
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

        String path = this.getClass().getResource("/").getPath();

        StringBuffer parentTemplateSb = new StringBuffer();
        BufferedReader parentTemplateReader = new BufferedReader(new FileReader(path
                + "template" + File.separator + "mybatisGeneratorConfig.tmp"));
        String s;
        while ((s = parentTemplateReader.readLine()) != null) {
            parentTemplateSb.append(s.trim() + " ");
        }
        String parentConfigContent = parentTemplateSb.toString();

        StringBuffer tableTemplateSb = new StringBuffer();
        BufferedReader tableTemplateReader = new BufferedReader(new FileReader(path
                + "template" + File.separator + "mybatisGeneratorTableConfig.tmp"));
        while ((s = tableTemplateReader.readLine()) != null) {
            tableTemplateSb.append(s.trim() + " ");
        }
        String tableConfigContent = tableTemplateSb.toString();

        String configXml = TemplateRender.renderTemplate(parentConfigContent, tableConfigContent, req);

        byte[] codeZip = Wr1ShellRunner.runCodeGenerator(new CharArrayReader(configXml.toCharArray()));

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attchement;filename=code.zip");
            HttpStatus statusCode = HttpStatus.OK;
            ResponseEntity<byte[]> entity = new ResponseEntity<>(codeZip, headers, statusCode);
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
