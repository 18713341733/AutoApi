package com.example.autoapi.check;

import com.example.autoapi.annotation.CaseTag;
import com.example.autoapi.annotation.CheckPoint;
import com.example.autoapi.exception.IllegaFormatException;
import com.example.autoapi.util.RequireUtil;

import java.lang.reflect.Method;

public class CheckPointCheck implements AnnotationCheckObserver{
    @Override
    public void check(Method method){
        CheckPoint[] checkPoints = method.getAnnotationsByType(CheckPoint.class);
        // 判断是否有CaseTag注解
        // 因为可以写多个，这里获取的是一个数组
        // 数组长度为0时，报异常
        if(checkPoints.length==0){
            throw new IllegaFormatException("CheckPoint is must");
        }
        for(CheckPoint checkPoint:checkPoints){

            RequireUtil.requireNotNullOrEmpty(checkPoint.value(),"CheckPoint is must.eg: @CheckPoint(xxx)");
        }

    }
}
