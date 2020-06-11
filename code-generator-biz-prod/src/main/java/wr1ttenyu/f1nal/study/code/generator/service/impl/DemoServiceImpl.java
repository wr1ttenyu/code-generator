package wr1ttenyu.f1nal.study.code.generator.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import wr1ttenyu.f1nal.study.code.generator.project.archetype.model.SayHiModel;
import wr1ttenyu.f1nal.study.code.generator.project.archetype.model.UserModel;
import wr1ttenyu.f1nal.study.code.generator.service.DemoService;

import java.time.LocalDateTime;

@Service
public class DemoServiceImpl implements DemoService {

    private static Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

   /*@Autowired
    private UUserMapper userMapper;*/

    @Override
    public SayHiModel sayHi(SayHiModel model, UserModel userModel) {
        model.setResMsg("hi," + userModel.getName() + " gogogogo~~~~");
        model.setResTime(LocalDateTime.now());
        return model;
    }
}
