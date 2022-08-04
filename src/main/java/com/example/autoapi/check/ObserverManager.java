package com.example.autoapi.check;

import com.google.common.collect.Lists;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ObserverManager {

    private final List<AnnotationCheckObserver> observers;

    // 构造器，私有，不能被new
    private ObserverManager(){
        observers = Lists.newArrayList(
                new CaseTitleCheck(),
                new CaseDescCheck(),
                new CaseTagCheck(),
                new CheckPointCheck(),
                new CaseGroupCheck()
        );
    }

    // ClassHolder属于静态内部类，在加载类Demo03的时候，只会加载内部类ClassHolder，
    // 但是不会把内部类的属性加载出来
    private static class ClassHolder{
        // 这里执行类加载，是jvm来执行类加载，它一定是单例的，不存在线程安全问题
        // 这里不是调用，是类加载，是成员变量
        private static final ObserverManager holder =new ObserverManager();

    }

    public static ObserverManager of(){//第一次调用getInstance()的时候赋值
        return ClassHolder.holder;
    }

    public void check(Method method){
        // 把case/方法 method ，依次接受各种检查
        for(AnnotationCheckObserver observer:observers){
            observer.check(method);
        }
    }



}
