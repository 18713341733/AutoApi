package com.example.autoapi.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Data
public class DataEntity {
    private List<Entity> entities;
    private Map<String,Entity> entityMap;
    // 这里为啥要都一个Map<String,Entity> entityMap
    // 将每个Entity，的key ，和他本身，放入这个Map
    // 为了将来方便查找整个对象下，是否有某个值。key-->Entity。

    // 构造器
    public DataEntity(){
        entities = new ArrayList<>();
        entityMap = new HashMap<>();
    }

    // 添加
    public void addEntity(Entity entity){
        entities.add(entity);
        entityMap.put(entity.getKey(),entity);
    }

    // 判断整个对象DataEntity里是否有某个值key
    public boolean hasKey(String str){
        return entityMap.containsKey(str);
    }

    // 根据key，取出值
    public Entity getKey(String key){
        return entityMap.get(key);
    }
}


