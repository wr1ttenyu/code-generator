package wr1ttenyu.f1nal.study.code.generator.test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import wr1ttenyu.f1nal.study.code.generator.Application;

@RunWith(SpringRunner.class)
@ActiveProfiles("")
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public abstract class BaseTest {
}
