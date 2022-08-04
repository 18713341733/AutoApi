package com.example.autoapi.annotation;

import com.example.autoapi.report.ReportType;
import com.example.autoapi.report.callback.ReportCallBack;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD}) // Target表示将来这个注解应用在哪些方面。注解可以应用在类上、方法上（此处只应用在方法上）
@Retention(RetentionPolicy.RUNTIME) //运行时，使用这个这个注解
public @interface ReportConfig {
    String token();
    ReportType reportType() default ReportType.DING_TALK;
    Class<? extends ReportCallBack> callback();
    String template() default "default_report_template";

}

