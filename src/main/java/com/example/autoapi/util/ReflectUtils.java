package com.example.autoapi.util;


import java.lang.reflect.Method;

// 这个工具，就是反射工具
// 通过各种方式，反射，拿到实例
// 我们这里只是多处理了异常，这样调用的时候，就不用处理异常了
public class ReflectUtils {
    // 反射，通过class 拿到实例
    public static <T> T newInstance(Class<T> clszz){
        try {
            return clszz.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    // 反射，通过String的全类名className拿到这个类
    // 再通过方法名，拿到这个方法
    public static Method getMethod(String className,String methodName){
        try {
            Class<?> cls = Class.forName(className);
            return cls.getMethod(methodName);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }
}
