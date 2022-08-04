package com.example.autoapi.model;

import lombok.Builder;
import lombok.Data;

// 用于保存报错信息
@Data
@Builder
public class FailureResult {
    private String className;
    private String methodName;
    private String parameterTypes;
    private Throwable throwable;
}
