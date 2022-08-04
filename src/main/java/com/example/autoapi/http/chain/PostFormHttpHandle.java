package com.example.autoapi.http.chain;

import com.example.autoapi.model.ContentType;
import com.example.autoapi.model.HttpRequest;
import com.example.autoapi.model.MethodType;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.HashMap;
import java.util.Map;

public class PostFormHttpHandle extends AbstractHttpHandle{
    @Override
    protected boolean preHttp(HttpRequest request) {
        // 判断是否能处理
        return request.getContentType()== ContentType.FORM && request.getMethodType()== MethodType.POST;
    }

    @Override
    protected Request createRequest(HttpRequest httpRequest) {
        FormBody.Builder builder= new FormBody.Builder();
        for (Map.Entry<String,Object> entry:httpRequest.getParams().entrySet()){
            builder.add(entry.getKey(), String.valueOf(entry.getValue()));
        }
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url(httpRequest.getUrl())
                .headers(Headers.of(httpRequest.getHeaders()==null?new HashMap<>():httpRequest.getHeaders()))
                .post(requestBody)
                .build();
        return request;
    }

}
