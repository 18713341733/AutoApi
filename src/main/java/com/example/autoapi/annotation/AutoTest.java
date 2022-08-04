package com.example.autoapi.annotation;

import com.example.autoapi.extension.CaseAnnotationCheckExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD}) // Target表示将来这个注解应用在哪些方面。注解可以应用在类上、方法上（此处只应用在方法上）
@Retention(RetentionPolicy.RUNTIME) //运行时，使用这个这个注解
@Test
@ExtendWith(CaseAnnotationCheckExtension.class) // 与junit结合，由junit提供。自定义一个扩展类，然后放到这里。走我们的自定义类
public @interface AutoTest {
}
