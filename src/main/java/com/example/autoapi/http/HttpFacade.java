package com.example.autoapi.http;

import com.example.autoapi.http.chain.HttpHandleManager;
import com.example.autoapi.http.service.HttpService;
import com.example.autoapi.model.HttpRequest;
import com.example.autoapi.model.HttpResponse;

public class HttpFacade {
    public static String doPostJson(String url,Object data){
        return new HttpService().doPostJson(url, data);
    }

    public static HttpResponse doRequest(HttpRequest httpRequest){
        return HttpHandleManager.of().doRequest(httpRequest);
    }
}
