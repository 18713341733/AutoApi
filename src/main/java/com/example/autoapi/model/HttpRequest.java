package com.example.autoapi.model;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@ToString
@Getter
public class HttpRequest {
    private String url;
    private Map<String,String> headers;
    private Object data;
    private Map<String,Object> params;
    private MethodType methodType;
    private ContentType contentType;

    // 这里自己写一个建造者模式，不直接用@Builder，是为了方便自己扩展

    // 一个静态的Builder
    public static Builder builder(){
        return new Builder();
    }

    // 构造器
    public HttpRequest(Builder builder){
        this.url = builder.url;
        this.headers = builder.headers;
        this.data = builder.data;
        this.params = builder.params;
        this.methodType = builder.methodType;
        this.contentType = builder.contentType;
    }

    // 这是一个内部类
    public static class Builder{
        private String url;
        private Map<String,String> headers;
        private Object data;
        private Map<String,Object> params;
        private MethodType methodType;
        private ContentType contentType;

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder headers(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public Builder params(Map<String, Object> params) {
            this.params = params;
            return this;
        }

        public Builder methodType(MethodType methodType) {
            this.methodType = methodType;
            return this;
        }

        public Builder post() {
            this.methodType = MethodType.POST;
            return this;
        }
        public Builder postForm() {
            this.methodType = MethodType.POST;
            this.contentType = ContentType.FORM;
            return this;
        }
        public Builder postJson() {
            this.methodType = MethodType.POST;
            this.contentType = ContentType.JSON;
            return this;
        }

        public Builder get() {
            this.methodType = MethodType.GET;
            return this;
        }

        public Builder contentType(ContentType contentType) {
            this.contentType = contentType;
            return this;
        }
        public Builder json() {
            this.contentType = ContentType.JSON;
            return this;
        }
        public Builder form() {
            this.contentType = ContentType.FORM;
            return this;
        }

        public HttpRequest build(){
            return new HttpRequest(this);
        }
    }

}
