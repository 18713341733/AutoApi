package com.example.autoapi.extension;

import com.example.autoapi.annotation.CaseTitle;
import com.example.autoapi.check.ObserverManager;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;

// 自定义的扩展类，必须实现框架提供的BeforeTestExecutionCallback接口
// BeforeTestExecutionCallback 在执行case之前，先执行我们的回调
public class CaseAnnotationCheckExtension implements BeforeTestExecutionCallback {
    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        Method requiredTestMethod = extensionContext.getRequiredTestMethod(); //拿到使用该注解的方法/测试case
        ObserverManager.of().check(requiredTestMethod); // 利用观察者模式对case上的方法依次进行检查

    }
}
