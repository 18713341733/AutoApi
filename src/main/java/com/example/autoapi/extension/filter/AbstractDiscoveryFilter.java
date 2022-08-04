package com.example.autoapi.extension.filter;

import com.example.autoapi.annotation.CaseSelector;
import org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor;
import org.junit.platform.engine.FilterResult;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.launcher.PostDiscoveryFilter;

public abstract class AbstractDiscoveryFilter implements PostDiscoveryFilter {
    // preFilter 判断是否执行当前过滤器，为true执行，为false不执行
    // onApply,具体的执行过滤，preFilter 为true时，执行onApply

    protected CaseSelector caseSelector;

    public AbstractDiscoveryFilter(CaseSelector caseSelector) {
        this.caseSelector = caseSelector;
    }

    protected abstract boolean preFilter(CaseSelector caseSelector);
    // 类似于责任链的设计模式。是否需要当前过滤器执行
    // 为true，需要执行当前过滤器，则调用下方的onApply方法，去实行过滤
    // 为false，不需要执行当前过滤器。交给下一个过滤器去执行
    // 这个方法是抽象的，交给子类去实现


    protected abstract FilterResult onApply(TestMethodTestDescriptor testDescriptor);

    @Override
    public FilterResult apply(TestDescriptor testDescriptor) {
        if(!preFilter(caseSelector)){ // 如果不需要过滤
            return FilterResult.includedIf(true); // 返回true，放过所有的case。
        }
        if(testDescriptor instanceof TestMethodTestDescriptor){
            return onApply((TestMethodTestDescriptor)testDescriptor);
        }
        return FilterResult.includedIf(false);// false 全部拦住，正常情况下不会出现这种情况
    }
}
