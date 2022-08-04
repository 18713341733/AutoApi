package com.example.autoapi.util;

import com.example.autoapi.model.DataEntity;
import com.example.autoapi.model.Entity;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// 解析yml，用的com.fasterxml.jackson jar包
public class YmlUtil {

    private static final ObjectMapper MAPPER;
    // 解析yml，用的com.fasterxml.jackson jar包
    // 将yml 转成json
    // 模式化写法，这么用就行
    static {
        YAMLFactory factory = new YAMLFactory();
        YAMLMapper mapper = new YAMLMapper(factory);
        MAPPER = mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,false);
    }

    public static Map<String,String> readToMap(String path){
        Map<String,String> map = Maps.newHashMap();
        try {
            InputStream inputStream = Resources.getResource(path).openStream();
            JsonNode jsonNode = MAPPER.readTree(inputStream);
            jsonNode.fields().forEachRemaining(stringJsonNodeEntry ->{
                map.put(stringJsonNodeEntry.getKey(), stringJsonNodeEntry.getValue().asText());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    // 读取文件
    public static List<DataEntity> read(String path){

        List<DataEntity> dataEntities = new ArrayList<>();

        // 判断路径非空
        RequireUtil.requireNotNullOrEmpty(path,"path is not null");

        try {
            InputStream inputStream = Resources.getResource(path).openStream();
            // 读取yml，将yml转成json
            JsonNode jsonNode = MAPPER.readTree(inputStream);
            // jsonNode = [{"userId":1234,"password":"123456"},{"userId":135,"password":"a123453336"},{"userId":1234567,"password":"b123456"}]
            // jsonNode 是一个集合，每个元素是一个json
            // jsonNode = {"userId":1234,"password":"123456"}
            // jsonNode也可能是一个对象
            if(jsonNode.isArray()){
                // 是数组，则遍历
                for(JsonNode node:jsonNode){
                    dataEntities.add(fromNodeToDataEntity(node));
                }
            }
            if(jsonNode.isObject()){
                dataEntities.add(fromNodeToDataEntity(jsonNode));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataEntities;
    }

    private static DataEntity fromNodeToDataEntity(JsonNode jsonNode){
        // 将读取的yaml数据jsonNode 转换成我们自定义类型DataEntity
        DataEntity dataEntity = new DataEntity();
        jsonNode.fields().forEachRemaining(stringJsonNodeEntry ->
                dataEntity.addEntity(Entity.builder()
                        .key(stringJsonNodeEntry.getKey())
                        .value(stringJsonNodeEntry.getValue().toString())
                        .isObject(stringJsonNodeEntry.getValue().isObject())
                        .build()));

        return dataEntity;

    }

    public static void main(String[] args) {
        List<DataEntity> read = read("data/demo1.yml");
        System.out.println("read = " + read);
    }


}
