package com.example.autoapi.extension.filter;

import com.example.autoapi.annotation.CaseGroup;
import com.example.autoapi.annotation.CaseSelector;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor;
import org.junit.platform.engine.FilterResult;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.launcher.PostDiscoveryFilter;

import java.lang.reflect.Method;

public class CaseGroupFilter extends AbstractDiscoveryFilter {
    public CaseGroupFilter(CaseSelector caseSelector) {
        super(caseSelector);
    }

    @Override
    protected boolean preFilter(CaseSelector caseSelector) {
        return StringUtils.isNotBlank(caseSelector.group())&&StringUtils.isNotBlank(caseSelector.team());
        // 判断注解CaseSelector 的group与team是否为空，若都不为空 ，启用我们这个筛选器
    }

    @Override
    protected FilterResult onApply(TestMethodTestDescriptor testDescriptor) {
        Method testMethod = testDescriptor.getTestMethod();
        // 该注解是非必填的，所以这里判断一下注解是否存在。
        // 其实我个人觉得这里没必要判断是否存在，因为用的这个模式，在场景里，需要调用到这个方法的时候，该注解肯定是存的
        boolean caseGroupSet = testMethod.isAnnotationPresent(CaseGroup.class);
        if(caseGroupSet){
            CaseGroup caseGroup = testMethod.getAnnotation(CaseGroup.class);
            if(caseGroup.group().equals(caseSelector.group())&&caseGroup.team().equals(caseSelector.team())){
                return FilterResult.includedIf(true);
            }
        }
        return FilterResult.includedIf(false);
    }


}
