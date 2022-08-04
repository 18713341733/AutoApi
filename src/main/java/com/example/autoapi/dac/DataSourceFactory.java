package com.example.autoapi.dac;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.autoapi.util.YmlUtil;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DataSourceFactory {
    // 这个map做缓冲用的？
    private Map<String, DataSource> dataSourceMap;

    // 构造器，私有，不能被new
    private DataSourceFactory(){
        dataSourceMap = new HashMap<>();

    }

    // ClassHolder属于静态内部类，在加载类Demo03的时候，只会加载内部类ClassHolder，
    // 但是不会把内部类的属性加载出来
    private static class ClassHolder{
        // 这里执行类加载，是jvm来执行类加载，它一定是单例的，不存在线程安全问题
        // 这里不是调用，是类加载，是成员变量
        private static final DataSourceFactory holder =new DataSourceFactory();

    }

    public static DataSourceFactory of(){//第一次调用getInstance()的时候赋值
        return ClassHolder.holder;
    }

    public DataSource getPoolDataSource(){
        String configPath = "conf/config-test.yml";
        Map<String, String> map = YmlUtil.readToMap(configPath);
        // ali 提供的DataSource
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(map.get("url"));
        dataSource.setUsername(map.get("username"));
        dataSource.setPassword(map.get("password"));
        dataSource.setDriverClassName(map.get("driver-class-name"));
//        dataSource.setDriverClassName(Driver.class.getName());
        // 连接池数量、超时时间等等配置，可以去git上看一下阿里推荐的配置
        return dataSource;
    }


}
