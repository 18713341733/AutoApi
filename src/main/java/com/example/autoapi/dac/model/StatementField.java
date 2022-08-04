package com.example.autoapi.dac.model;

// 用这个表示sql语句的一个条件，如where id = 1

public class StatementField extends FieldEntity{

    private StatementOperator operator;

//    public StatementField(String fieldName, Object fieldValue) {
//        super(fieldName, fieldValue);
//    }

    public StatementField(String fieldName, Object fieldValue,StatementOperator operator) {
        super(fieldName, fieldValue);
        this.operator = operator;
    }

    public StatementOperator getOperator() {
        return operator;
    }
}
