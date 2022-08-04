package com.example.autoapi.extension.filter;

import com.example.autoapi.annotation.CaseSelector;
import com.example.autoapi.annotation.CaseTag;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor;
import org.junit.platform.engine.FilterResult;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.launcher.PostDiscoveryFilter;

import java.lang.reflect.Method;
import java.util.Arrays;

public class CaseTagFilter extends AbstractDiscoveryFilter{


    public CaseTagFilter(CaseSelector caseSelector) {
        super(caseSelector);
    }

    @Override
    protected boolean preFilter(CaseSelector caseSelector) {
        return StringUtils.isNotBlank(caseSelector.key())&& StringUtils.isNotBlank(caseSelector.val());
    }

    @Override
    protected FilterResult onApply(TestMethodTestDescriptor testDescriptor) {
        Method testMethod = testDescriptor.getTestMethod();
        CaseTag[] caseTags = testMethod.getAnnotationsByType(CaseTag.class);
        long count = Arrays.stream(caseTags)
                .filter(caseTag -> caseTag.key().equals(caseSelector.key()) && caseTag.val().equals(caseSelector.val()))
                .count();
        return count>0 ? FilterResult.includedIf(true):FilterResult.includedIf(false);

    }

}
