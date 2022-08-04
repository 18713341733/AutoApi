package com.example.autoapi.annotation;

import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD}) // Target表示将来这个注解应用在哪些方面。注解可以应用在类上、方法上（此处只应用在方法上）
@Retention(RetentionPolicy.RUNTIME) //运行时，使用这个这个注解
@Repeatable(CheckPoints.class) // 支持写多个注解@CheckPoint()@CheckPoint()
public @interface CheckPoint {
    String value();
}
