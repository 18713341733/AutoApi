package com.example.autoapi.template;

import com.google.common.io.Resources;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class TemplateFactory {
    // 所有模版存放的文件夹名称
    public final static String TEMPLATE_ROOT_PATH = "templates";

    private Map<String,String> templateMap;
    // 构造器，私有，不能被new
    private TemplateFactory(){
        templateMap = initTemplateMap();

    }

    private Map<String,String> initTemplateMap(){
        // 读取templates文件夹下所有的模版文件
        // 将文件名与文件内容构建成key-value 的map，方便取数据

        // 获取存放模版文件夹的路径
        String rootPath = Resources.getResource(TEMPLATE_ROOT_PATH).getPath();
        File file = new File(rootPath);
        // 获取文件夹内所有是文件的文件
        File[] files = file.listFiles(f -> f.isFile()); // 判断是否文件，是则加入数组
        // 将文件名与文件内容组成map
        return Arrays.stream(files).collect(Collectors.toMap(f->f.getName(),f->getData(f)));
    }


    // 读取文件里的内容，并返回字符串
    private String getData(File f){
        try {
            return FileUtils.readFileToString(f,"UTF-8");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public String getTemplate(String key){
        return templateMap.get(key);
    }



    // ClassHolder属于静态内部类，在加载类Demo03的时候，只会加载内部类ClassHolder，
    // 但是不会把内部类的属性加载出来
    private static class ClassHolder{
        // 这里执行类加载，是jvm来执行类加载，它一定是单例的，不存在线程安全问题
        // 这里不是调用，是类加载，是成员变量
        private static final TemplateFactory holder =new TemplateFactory();

    }

    public static TemplateFactory of(){//第一次调用getInstance()的时候赋值
        return ClassHolder.holder;
    }






}
