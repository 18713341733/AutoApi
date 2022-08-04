package com.example.autoapi.template;

import java.util.Map;

public class TemplateFacade {
    public static String getTemplate(String key){
        return new TemplateService().getTemplateService(key);
    }

    public static String replaceTemplate(String key, Map<String,Object> map){
        return new TemplateService().replaceTemplate(key,map);
    }
}
