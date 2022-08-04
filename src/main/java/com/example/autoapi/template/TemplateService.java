package com.example.autoapi.template;

import com.example.autoapi.util.RequireUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class TemplateService {
    public String getTemplateService(String key){
        RequireUtil.requireNotNullOrEmpty(key,"key is not empty");
        return TemplateFactory.of().getTemplate(key);
    }

    public String replaceTemplate(String key, Map<String,Object> map){
        // 1、读取模版文件里的String template
        // 2、数据传进来是一个map
        // 遍历map，拿到map的每一个entry,取里面的key和value
        // 3、开始替换，将template 里面的getTag(entry.getKey()) 替换成map的value
        String template = getTemplateService(key);
        for(Map.Entry<String,Object> entry: map.entrySet()){
            template=StringUtils.replace(template,getTag(entry.getKey()),String.valueOf(entry.getValue()));

        }

        return template;

    }

    private String getTag(String key){
        // 将case_title 转换成 ${case_title}
        return String.format("${%s}",key);
    }


}
