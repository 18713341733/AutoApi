package com.example.autoapi.cases.car;

import com.example.autoapi.annotation.*;

public class TestCar {


    @AutoTest // 就不需要使用框架自带注解@Test，使用我们自定义注解
    @CaseDesc(desc="正常case",owner = "pang") // 描述和管理人
    @CaseTitle("xx") // case的标题
    @CheckPoint("aaa") // 检查点，可以是多个
    @CheckPoint("bbb")
    @CaseTag(key = "level",val = "redLine") // 用于扩展注解，自己想放啥放啥
    @CaseTag(key = "level",val = "P1")
    public void test111(){
        System.out.println("TestCar.test1");
    }




    @AutoTest // 就不需要使用框架自带注解@Test，使用我们自定义注解
    @CaseDesc(desc="2222",owner = "2222") // 描述和管理人
    @CaseTitle("xx") // case的标题
    @CheckPoint("aaa") // 检查点，可以是多个
    @CheckPoint("bbb")
    @CaseTag(key = "level",val = "normal") // 用于扩展注解，自己想放啥放啥
    @CaseTag(key = "level",val = "P1")
    public void test3333(){
        System.out.println("TestLogin.test3");
    }


}
