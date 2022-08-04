package com.example.autoapi.dac.model;

public enum StatementOperator {
    EQ("="),
    MORE(">"),
    LESS("<") ;

    // 构造器
    StatementOperator(String operator) {
        this.operator = operator;

    }

    public String getOperator() {
        return operator;
    }

    // 属性
    private String operator;

}
