package com.example.autoapi.dac.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

// SQL 语句中的所有条件
// 条件类
@Data
public class StatementBuild {
    private List<StatementField> statementFields;

    public StatementBuild(){
        statementFields = new ArrayList<>();
    }

    public static StatementBuild builder(){
        return new StatementBuild();
    }

    public StatementBuild addStatement(StatementField statementField){
        statementFields.add(statementField);
        return this;
    }

    // 语句条件，这里默认给一个=
    public StatementBuild addStatement(String fieldName,String fieldValue){
        return addStatement(new StatementField(fieldName,fieldValue,StatementOperator.EQ));
    }

    // 语句条件，这里调用枚举
    public StatementBuild addStatement(String fieldName,String fieldValue,StatementOperator operator){
        return addStatement(new StatementField(fieldName,fieldValue,operator));
    }
}
