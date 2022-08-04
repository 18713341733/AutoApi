package com.example.autoapi.test;

import com.example.autoapi.annotation.*;

public class TestDemo {


    @AutoTest // 就不需要使用框架自带注解@Test，使用我们自定义注解
    @CaseDesc(desc="正常case",owner = "pang") // 描述和管理人
    @CaseTitle("xx") // case的标题
    @CheckPoint("aaa") // 检查点，可以是多个
    @CheckPoint("bbb")
    @CaseTag(key = "level",val = "P0") // 用于扩展注解，自己想放啥放啥
    @CaseTag(key = "level",val = "P1")
//    @DingTalkAlarm(alarmCallBack = DefaultAlarmCallBack.class)
    public void test1(){
        System.out.println("TestDemo.test1");
        int i = 1/0;


    }

}
