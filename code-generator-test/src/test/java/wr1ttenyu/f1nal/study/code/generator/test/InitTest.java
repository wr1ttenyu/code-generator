package wr1ttenyu.f1nal.study.code.generator.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wr1ttenyu.f1nal.study.code.generator.entity.MSayHi;
import wr1ttenyu.f1nal.study.code.generator.service.DemoService;
import wr1ttenyu.f1nal.study.code.generator.util.UUIDGenerator;

public class InitTest extends BaseTest {

    @Autowired
    private MSayHiMapper userMapper;

    @Autowired
    private DemoService demoService;

    @Test
    public void testUUserMapper() {
        MSayHi user = userMapper.selectByPrimaryKey("1");
        System.out.println(user);
    }

    @Test
    public void testUUserMapperAdd() {
        MSayHi user = userMapper.selectByPrimaryKey("1");
        user.setId(UUIDGenerator.generate());
        userMapper.insert(user);
    }

    @Test
    public void testUserService() {
        demoService.getUserById("100");
    }
}
