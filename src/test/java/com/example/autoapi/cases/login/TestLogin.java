package com.example.autoapi.cases.login;

import com.example.autoapi.annotation.*;
import org.junit.jupiter.api.Assertions;

public class TestLogin {


    @AutoTest // 就不需要使用框架自带注解@Test，使用我们自定义注解
    @CaseDesc(desc="正常case",owner = "pang") // 描述和管理人
    @CaseTitle("xx") // case的标题
    @CheckPoint("aaa") // 检查点，可以是多个
    @CheckPoint("bbb")
    @CaseTag(key = "level",val = "redLine") // 用于扩展注解，自己想放啥放啥
    @CaseTag(key = "level",val = "P1")
    @CaseGroup(team="qa",group="normal")
//    @DingTalkAlarm
    public void test1(){
        System.out.println("TestLogin.test1");
        int i = 1/ 0;
//        System.out.println(i);
    }



    @AutoTest // 就不需要使用框架自带注解@Test，使用我们自定义注解
    @CaseDesc(desc="1111",owner = "111") // 描述和管理人
    @CaseTitle("xx") // case的标题
    @CheckPoint("aaa") // 检查点，可以是多个
    @CheckPoint("bbb")
    @CaseTag(key = "level",val = "redLine") // 用于扩展注解，自己想放啥放啥
    @CaseTag(key = "level",val = "P1")
    @CaseGroup(team="dev",group="normal")
//    @DingTalkAlarm(alarmCallBack = DefaultAlarmCallBack.class)
    public void test2(){
        System.out.println("TestLogin.test2");
        Assertions.assertEquals(1,2);
    }



    @AutoTest // 就不需要使用框架自带注解@Test，使用我们自定义注解
    @CaseDesc(desc="2222",owner = "2222") // 描述和管理人
    @CaseTitle("xx") // case的标题
    @CheckPoint("aaa") // 检查点，可以是多个
    @CheckPoint("bbb")
    @CaseTag(key = "level",val = "normal") // 用于扩展注解，自己想放啥放啥
    @CaseTag(key = "level",val = "P1")
    @CaseGroup(team="qa",group="fail")
    public void test3(){
        System.out.println("TestLogin.test3");
    }


}
