package com.example.autoapi.cases.module.param;

import com.example.autoapi.annotation.*;

public class TestLogin {



    @CaseDesc(desc="正常case",owner = "pang") // 描述和管理人
    @CaseTitle("xx") // case的标题
    @CheckPoint("aaa") // 检查点，可以是多个
    @CheckPoint("bbb")
    @CaseTag(key = "level",val = "redLine") // 用于扩展注解，自己想放啥放啥
    @CaseTag(key = "level",val = "P1")
    @CaseGroup(team="qa",group="normal")
    @DataYml(path="data/demo1.yml")
    public void test1(int userId,String password){
        System.out.println("TestLogin.test1");
        login(userId, password);
    }

    public void login(int userId,String password){
        System.out.println("userId = " + userId);
        System.out.println("password = " + password);
        System.out.println("TestLogin.login");
    }

    @CaseDesc(desc="正常case",owner = "pang") // 描述和管理人
    @CaseTitle("xx") // case的标题
    @CheckPoint("aaa") // 检查点，可以是多个
    @CheckPoint("bbb")
    @CaseTag(key = "level",val = "redLine") // 用于扩展注解，自己想放啥放啥
    @CaseTag(key = "level",val = "P1")
    @CaseGroup(team="qa",group="normal")
    @DataYml(path="data/demo2.yml")
    public void test2(int chanId,Order order){
        System.out.println("chanId = " + chanId);
        System.out.println("order = " + order);
    }
}



