package com.example.autoapi.extension;

import com.example.autoapi.annotation.DataYml;
import com.example.autoapi.model.DataEntity;
import com.example.autoapi.model.Entity;
import com.example.autoapi.util.YmlUtil;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class DataYmlExtension implements TestTemplateInvocationContextProvider {
    @Override
    public boolean supportsTestTemplate(ExtensionContext extensionContext) {
        // 类似于我们写的preHttp,先判断是否能够处理
        // 这里判断是否能参数化，能则继续处理

        // 这里都是基于反射，拿到测试case
        Optional<Method> testMethod = extensionContext.getTestMethod();

        // 判断方法上是否有DataYml.class注解
        boolean present = testMethod.filter(method -> AnnotationSupport.isAnnotated(method, DataYml.class)).isPresent();
        // 这里的filter与isPresent不是流里的提供的功能，是Optional提供的
        // 过滤，注解为DataYml.class的注解，存在则返回true
        return present;
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext extensionContext) {
        // 1、 DataYml 取path
        // 2、解析Yml -> T list<T>
        // 3、T -> userId pwd
        // 4、循环调用

        // 通过反射，拿到方法
        Method testMethod = extensionContext.getRequiredTestMethod();
        // 通过方法，拿到方法上的DataYml注解
        DataYml dataYml = testMethod.getAnnotation(DataYml.class);
        // 拿到DataYml注解的path
        String path = dataYml.path();
        List<DataEntity> dataEntities = YmlUtil.read(path);
//        System.out.println("path = " + path);

        // 返回值 public Stream<TestTemplateInvocationContext>
        // TestTemplateInvocationContext 是一个接口
        // 我们要实现这个接口，自定义一个类
        // 将从yaml读取的数据，转换成这个我们自定义的类

        return dataEntities.stream().map(dataEntity -> new DataYmlInvocationContext(dataEntity));
    }

    // 将从yaml读取的数据，转换成这个我们自定义的类
    static class DataYmlInvocationContext implements TestTemplateInvocationContext, ParameterResolver {
        private DataEntity dataEntity;
        public DataYmlInvocationContext(DataEntity dataEntity){
            this.dataEntity = dataEntity;
        }

        // 实现的TestTemplateInvocationContext这个接口
        // 将ParameterResolver的子类 与TestTemplateInvocationContext的子类关联在一起
        @Override
        public List<Extension> getAdditionalExtensions() {
            return Lists.newArrayList(this);
        }

        // 实现的TestTemplateInvocationContext这个接口
        @Override
        public String getDisplayName(int invocationIndex) {
            return "data provider:"+invocationIndex;
        }

        // 实现的ParameterResolver这个接口
        @Override
        public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
            return true;
        }

        // 实现的ParameterResolver这个接口
        @Override
        public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
            // 反射
            Parameter parameter = parameterContext.getParameter();
            String key = parameter.getName();
            if(dataEntity.hasKey(key)){
                Entity entity = dataEntity.getKey(key);
                if(entity.isObject()){
                    // 如果 entity 是一个对象
                    // entity.getValue() 是一个json
                    // 这里将json 转成我们需要的对象，parameter.getType() 这个就是我们需要的对象类型
                    // 转成这个 parameter.getType()
                    return new Gson().fromJson(entity.getValue(),parameter.getType());
                }
                return toJavaTpye(entity.getValue(),parameter.getType());
            }
            // entity.getValue() 都是字符串
            // parameter.getType() 就是在写case时候的传参类型
            //  public void test1(int userId,String password)
            throw  new IllegalStateException("no has data");
        }

        private Object toJavaTpye(String value, Class<?> type) {
            // 根绝类型，转换成对应的类型
            // entity.getValue() 都是字符串
            // parameter.getType() 就是在写case时候的传参类型
            //  public void test1(int userId,String password)
            switch (type.getName()){
                case "int":
                    return Integer.parseInt(value);
                case "long":
                    return Long.parseLong(value);
                case "java.lang.String":
                    return value;
                case "double":
                    return Double.parseDouble(value);
                default:
                    throw new IllegalStateException("unsupport type");

            }


        }


    }
}
