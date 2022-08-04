package com.example.autoapi.check;

import com.example.autoapi.annotation.CaseDesc;
import com.example.autoapi.annotation.CaseGroup;
import com.example.autoapi.exception.IllegaFormatException;
import com.example.autoapi.util.RequireUtil;

import java.lang.reflect.Method;

public class CaseGroupCheck implements AnnotationCheckObserver{
    @Override
    public void check(Method method){
        boolean caseGroupSet = method.isAnnotationPresent(CaseGroup.class);
        // 判断注解CaseGroup是否存在
        // CaseGroup 非必填
        if(!caseGroupSet){
            return;
        }
        CaseGroup caseGroup = method.getAnnotation(CaseGroup.class);
        RequireUtil.requireNotNullOrEmpty(caseGroup.team(),"CaseGroup team is must.eg: @CaseGroup(team=\"xx\"，group=\"xx\")");
        RequireUtil.requireNotNullOrEmpty(caseGroup.group(),"CaseGroup group is must.eg: @CaseGroup(team=\"xx\"，group=\"xx\")");

    }
}
