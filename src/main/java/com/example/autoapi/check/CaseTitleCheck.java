package com.example.autoapi.check;

import com.example.autoapi.annotation.CaseTitle;
import com.example.autoapi.exception.IllegaFormatException;
import com.example.autoapi.util.RequireUtil;

import java.lang.reflect.Method;

public class CaseTitleCheck implements AnnotationCheckObserver{
    @Override
    public void check(Method method){
        boolean titleSet = method.isAnnotationPresent(CaseTitle.class);
        // 判断注解CaseTitle是否存在
        if(!titleSet){
            throw new IllegaFormatException("title is must");
        }
        CaseTitle caseTitle = method.getAnnotation(CaseTitle.class);
        RequireUtil.requireNotNullOrEmpty(caseTitle.value(),"CaseTitle value is must.eg: @CaseTitle(\"标题\")");
    }
}
