package com.example.autoapi.check;

import com.example.autoapi.annotation.CaseDesc;
import com.example.autoapi.annotation.CaseTitle;
import com.example.autoapi.exception.IllegaFormatException;
import com.example.autoapi.util.RequireUtil;

import java.lang.reflect.Method;

public class CaseDescCheck implements AnnotationCheckObserver{
    @Override
    public void check(Method method){
        boolean caseDescSet = method.isAnnotationPresent(CaseDesc.class);
        // 判断注解CaseTitle是否存在
        if(!caseDescSet){
            throw new IllegaFormatException("CaseDesc is must");
        }
        CaseDesc caseDesc = method.getAnnotation(CaseDesc.class);
        RequireUtil.requireNotNullOrEmpty(caseDesc.desc(),"CaseDesc desc is must.eg: @CaseDesc(desc=\"描述\"，owner=\"管理者\")");
        RequireUtil.requireNotNullOrEmpty(caseDesc.owner(),"CaseDesc owner is must.eg: @CaseDesc(desc=\"描述\"，owner=\"管理者\")");

    }
}
