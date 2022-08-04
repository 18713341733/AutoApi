package com.example.autoapi.http.service;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpService {
    // post json 无header方式
    public String doPostJson(String url,Object data){
        return doPostJson(url,null,data);
    }
    // post form 无header方式
    public String doPostForm(String url,Map<String,Object> params){
        return doPostForm(url,null,params);
    }


    public String doPostForm(String url,Map<String,String> headers,Map<String,Object> params){

        FormBody.Builder builder= new FormBody.Builder();
        for (Map.Entry<String,Object> entry:params.entrySet()){
            builder.add(entry.getKey(), String.valueOf(entry.getValue()));
        }
        RequestBody requestBody = builder.build();
        Request request = createRequest(url,headers,requestBody);
        return handleRequest(request);

    }

    public String doPostJson(String url, Map<String,String> headers,Object data){
        // 1、构建请求
        MediaType mediaType = MediaType.parse("application/json");
        // Gson是json工具类，将x转成json
        Gson gson = new Gson();
        RequestBody requestBody = RequestBody.create(gson.toJson(data), mediaType);
        Request request = createRequest(url,headers,requestBody);
        return handleRequest(request);

    }

    private Request createRequest(String url, Map<String,String> headers,RequestBody requestBody){
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(headers==null?new HashMap<>():headers))
                .post(requestBody)
                .build();
        return request;
    }

    private String handleRequest(Request request){
        OkHttpClient client = new OkHttpClient();

        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            String body = response.body().string();
            return body;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

}
