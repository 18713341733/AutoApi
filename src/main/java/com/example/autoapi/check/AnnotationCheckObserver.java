package com.example.autoapi.check;

import java.lang.reflect.Method;

// 观察者模式
// 每个注解的检查都实现这个接口，便于统一管理/校验注解
public interface AnnotationCheckObserver {
    void check(Method method);
}
