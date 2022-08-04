package com.example.autoapi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Entity {
    private String key;
    private String value;
    private boolean isObject;
}
