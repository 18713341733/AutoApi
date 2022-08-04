package com.example.autoapi.http.chain;

import com.example.autoapi.model.ContentType;
import com.example.autoapi.model.HttpRequest;
import com.example.autoapi.model.MethodType;
import com.google.gson.Gson;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.HashMap;

public class PostJsonHttpHandle extends AbstractHttpHandle{
    @Override
    protected boolean preHttp(HttpRequest request) {
        // 判断是否能处理
        return request.getContentType()== ContentType.JSON && request.getMethodType()== MethodType.POST;
    }


    // 这里的具体处理方式，不是去发送请求，而是构建request
    // 将 HttpRequest --》  Request
    // 构建post json 的request
    @Override
    protected Request createRequest(HttpRequest httpRequest) {
        MediaType mediaType = MediaType.parse("application/json");
        // Gson是json工具类，将x转成json
        Gson gson = new Gson();
        RequestBody requestBody = RequestBody.create(gson.toJson(httpRequest.getData()), mediaType);
        Request request = new Request.Builder()
                .url(httpRequest.getUrl())
                .headers(Headers.of(httpRequest.getHeaders()==null?new HashMap<>():httpRequest.getHeaders()))
                .post(requestBody)
                .build();
        return request;
    }

}
