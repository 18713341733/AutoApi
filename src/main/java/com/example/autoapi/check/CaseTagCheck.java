package com.example.autoapi.check;

import com.example.autoapi.annotation.CaseDesc;
import com.example.autoapi.annotation.CaseTag;
import com.example.autoapi.exception.IllegaFormatException;
import com.example.autoapi.util.RequireUtil;

import java.lang.reflect.Method;

public class CaseTagCheck implements AnnotationCheckObserver{
    @Override
    public void check(Method method){
        CaseTag[] caseTags = method.getAnnotationsByType(CaseTag.class);
        // 判断是否有CaseTag注解
        // 因为可以写多个，这里获取的是一个数组
        // 数组长度为0时，报异常
        if(caseTags.length==0){
            throw new IllegaFormatException("CaseTag is must");
        }
        for(CaseTag caseTag:caseTags){

            RequireUtil.requireNotNullOrEmpty(caseTag.key(),"CaseTag key is must.eg: @CaseTag(key=\"key\"，val=\"val\")");
            RequireUtil.requireNotNullOrEmpty(caseTag.val(),"CaseTag val is must.eg: @CaseTag(key=\"key\"，val=\"val\")");

        }

    }
}
