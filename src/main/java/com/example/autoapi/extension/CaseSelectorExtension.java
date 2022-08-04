package com.example.autoapi.extension;

import com.example.autoapi.alarm.FailureListener;
import com.example.autoapi.alarm.callback.AlarmCallBack;
import com.example.autoapi.annotation.CaseSelector;
import com.example.autoapi.annotation.DingTalkAlarm;
import com.example.autoapi.annotation.ReportConfig;
import com.example.autoapi.extension.filter.CaseGroupFilter;
import com.example.autoapi.extension.filter.CaseTagFilter;
import com.example.autoapi.model.FailureResult;
import com.example.autoapi.model.SummaryResult;
import com.example.autoapi.util.ReflectUtils;
import com.example.autoapi.util.RequireUtil;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.engine.TestSource;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.engine.support.descriptor.MethodSource;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

public class CaseSelectorExtension implements BeforeTestExecutionCallback{

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        // 获取执行入口的方法
        Method method = extensionContext.getRequiredTestMethod();
        // 获取注解CaseSelector信息
        CaseSelector caseSelector = method.getAnnotation(CaseSelector.class);
        // 验证/校验 注解信息
        verify(caseSelector);

        //----开始进行是筛选----
        // 先筛选包
        // 再按照@CaseTag key value筛选

        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                // 根据包筛选case
                .selectors(DiscoverySelectors.selectPackage(caseSelector.scanPackage()))
                // 根据注解CaseTag筛选case
                .filters(new CaseTagFilter(caseSelector))
                .filters(new CaseGroupFilter(caseSelector))
                .build();

        Launcher launcher = LauncherFactory.create();

        SummaryGeneratingListener summaryGeneratingListener = new SummaryGeneratingListener();

        // 判断执行入口，是否有报警注解
        boolean dingTalkSet = method.isAnnotationPresent(DingTalkAlarm.class);
        if(dingTalkSet){
            // 获取报警注解的属性
            // 我们把具体的报警方式，写在了这个注解的属性里
            DingTalkAlarm dingTalkAlarm = method.getAnnotation(DingTalkAlarm.class);
            Class<? extends AlarmCallBack> alarmCallBack = dingTalkAlarm.alarmCallBack();


            FailureListener failureListener = new FailureListener(dingTalkAlarm.token(),alarmCallBack);

            launcher.execute(request,summaryGeneratingListener,failureListener );

        } else {
            launcher.execute(request,summaryGeneratingListener );

        }
        // ----以上固定结构，框架提供能力
        // 根据CaseTag 筛选，这里是需要自己写的CaseTagFilter

        boolean reportSet= method.isAnnotationPresent(ReportConfig.class);
        if(reportSet){
            TestExecutionSummary summary = summaryGeneratingListener.getSummary();
            SummaryResult summaryResult = FromSummaryToSummaryResult(summary);
            ReportConfig reportConfig = method.getAnnotation(ReportConfig.class);
            ReflectUtils.newInstance(reportConfig.callback()).postReport(summaryResult, reportConfig.token(), reportConfig.template());

        }

    }

    // 将框架提供的运行结果类TestExecutionSummary，转换成我们自定义的结果类
    private SummaryResult FromSummaryToSummaryResult(TestExecutionSummary summary){
        // 失败的原因
        List<FailureResult> failureResults = summary.getFailures().stream()
                .map(failure -> {
                    TestIdentifier testIdentifier = failure.getTestIdentifier();
                    TestSource testSource = testIdentifier.getSource().get();
                    MethodSource methodSource = (MethodSource) testSource;
                    Throwable throwable = failure.getException();
                    // 将具体的一条失败case相关信息封装在FailureResult中
                    FailureResult failureResult = FailureResult.builder()
                            .className(methodSource.getClassName()) //报错的 类名
                            .methodName(methodSource.getMethodName()) //报错的方法名/case名
                            .parameterTypes(methodSource.getMethodParameterTypes()) // 传参类型
                            .throwable(throwable) // 报错原因
                            .build();
                    return failureResult;
                })
                .collect(Collectors.toList());
        SummaryResult summaryResult = SummaryResult.builder()
                .totalCount(summary.getTestsFoundCount())
                .failureCount(summary.getTotalFailureCount())
                .successCount(summary.getTestsSucceededCount())
                .startTime(summary.getTimeStarted())
                .endTime(summary.getTimeFinished())
                .failureResults(failureResults)
                .build();

        return summaryResult;

    }

    private void verify(CaseSelector caseSelector){
        RequireUtil.requireNotNullOrEmpty(caseSelector.scanPackage(),"scanPackage is must");


    }


}
