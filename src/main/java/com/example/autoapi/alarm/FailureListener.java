package com.example.autoapi.alarm;

import com.example.autoapi.alarm.callback.AlarmCallBack;
import com.example.autoapi.model.FailureResult;
import com.example.autoapi.util.ReflectUtils;
import lombok.AllArgsConstructor;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.TestSource;
import org.junit.platform.engine.support.descriptor.MethodSource;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;

import java.util.Optional;

// case运行完之后的回调 TestExecutionListener
@AllArgsConstructor
public class FailureListener implements TestExecutionListener {
    private String token;

    private  Class<? extends AlarmCallBack> alarmCallBack;

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        TestExecutionResult.Status status = testExecutionResult.getStatus();

        // 判断，若case 没有运行失败，则过滤掉。我们只做 失败case的报警
        if(status != TestExecutionResult.Status.FAILED){
            return;
        }

        // 我们从TestIdentifier testIdentifier 中获取具体需要报警的信息，比如case标题等等
        //Optional 优雅的处理none
        // 如果没有直接return
        Optional<TestSource> source = testIdentifier.getSource();
        if(!source.isPresent()){
            return;
        }

        TestSource testSource = source.get();

        // testSource instanceof MethodSource
        // 框架，不仅仅提供了具体case/方法的methodsource，还提供了这个case所属类的classsource。
        // 为啥要做这个判断，因为有的testSource是MethodSource，有的是ClassSource
        // 此处，我们只需要收集 具体case/方法的信息就可以了
        if(!(testSource instanceof MethodSource)){
            return;
        }
        // 强转成methodSource
        MethodSource methodSource = (MethodSource)testSource;
        Throwable throwable = testExecutionResult.getThrowable().get();

        // 将具体的一条失败case相关信息封装在FailureResult中
        FailureResult failureResult = FailureResult.builder()
                .className(methodSource.getClassName()) //报错的 类名
                .methodName(methodSource.getMethodName()) //报错的方法名/case名
                .parameterTypes(methodSource.getMethodParameterTypes()) // 传参类型
                .throwable(throwable) // 报错原因
                .build();


        ReflectUtils.newInstance(alarmCallBack).postAlarm(failureResult,token);
        /*
        ReflectUtils.newInstance(alarmCallBack).postAlarm(failureResult);
        与下面一致
        只是，自己写了一个工具而已，反射，通过class拿到实例。需要处理异常
         try {
            AlarmCallBack alarmCallBack = this.alarmCallBack.newInstance();
            alarmCallBack.postAlarm(failureResult);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

         */




    }
}
