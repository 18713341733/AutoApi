package com.example.autoapi.http.chain;

import com.example.autoapi.model.HttpRequest;
import com.example.autoapi.model.HttpResponse;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

// 抽象类
public abstract class AbstractHttpHandle {
    private AbstractHttpHandle httpHandle;

    public void setHttpHandle(AbstractHttpHandle httpHandle) {
        this.httpHandle = httpHandle;
    }

    // 抽象方法
    // 判断，当时是否能处理，ture，自己处理，false交给下一个处理
    protected abstract boolean preHttp(HttpRequest request);

    // 具体的
    protected abstract Request createRequest(HttpRequest request);


    // 抽象方法
    // 具体的处理逻辑
    protected HttpResponse onHttp(HttpRequest httpRequest){
        // 将我们自定义的httpRequest，转成请求要用的request
        Request request = createRequest(httpRequest);

        // 发送这个请求，并得到返回值
        return handleRequest(request);
    };


    // 发送请求的，得到返回值HttpResponse的固定写法
    private HttpResponse handleRequest(Request request){
        OkHttpClient client = new OkHttpClient();

        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            String body = response.body().string();
            return new HttpResponse(body);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }


    // 模版，规定好了整个流程的逻辑
    public HttpResponse doHttp(HttpRequest request){
        if(preHttp(request)){
            // 如果能处理，则直接处理
            return onHttp(request);
        }
        // 如果不能处理
        // 交给下一个处理
        if(httpHandle != null){
            return httpHandle.doHttp(request);

        }
        throw new IllegalStateException("unknow http request");

    }

}
