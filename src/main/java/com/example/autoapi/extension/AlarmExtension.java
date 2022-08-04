package com.example.autoapi.extension;

import com.example.autoapi.annotation.DingTalkAlarm;
import com.example.autoapi.model.FailureResult;
import com.example.autoapi.util.ReflectUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

//TestExecutionExceptionHandler 框架提供的能力，测试执行失败，触发的回调
public class AlarmExtension implements TestExecutionExceptionHandler {
    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
        Method testMethod = extensionContext.getRequiredTestMethod();
        Class<?> testClass = extensionContext.getRequiredTestClass();
        Class<?>[] parameterTypes = testMethod.getParameterTypes();
        System.out.println("AlarmExtension.handleTestExecutionException");
        String params = Arrays.stream(parameterTypes).map(cls->cls.getName()).collect(Collectors.joining(","));
        FailureResult failureResult = FailureResult.builder()
                .className(testClass.getName())
                .methodName(testMethod.getName())
                .parameterTypes(params)
                .throwable(throwable)
                .build();
        DingTalkAlarm dingTalkAlarm = testMethod.getAnnotation(DingTalkAlarm.class);
        ReflectUtils.newInstance(dingTalkAlarm.alarmCallBack()).postAlarm(failureResult,dingTalkAlarm.token());


    }
}
