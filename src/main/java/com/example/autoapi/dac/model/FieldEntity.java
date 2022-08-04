package com.example.autoapi.dac.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldEntity {
    // 这是一行数据 ，如 id=1
    private String fieldName;
    private Object fieldValue;
}
