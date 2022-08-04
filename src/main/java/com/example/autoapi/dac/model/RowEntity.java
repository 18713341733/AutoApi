package com.example.autoapi.dac.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class RowEntity {
    // 这是一条数据
    private List<FieldEntity> fields;

}
