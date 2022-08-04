package com.example.autoapi.http.chain;

import com.example.autoapi.model.HttpRequest;
import com.example.autoapi.model.HttpResponse;

public class HttpHandleManager {
    private AbstractHttpHandle httpHandle;

    // 构造器，私有，不能被new
    private HttpHandleManager(){
        httpHandle = initHandle();
    }

    private AbstractHttpHandle initHandle() {
        PostJsonHttpHandle postJsonHttpHandle = new PostJsonHttpHandle();
        PostFormHttpHandle postFormHttpHandle = new PostFormHttpHandle();

        // 将json与form串起来
        postJsonHttpHandle.setHttpHandle(postFormHttpHandle);
        return postJsonHttpHandle;
    }

    // ClassHolder属于静态内部类，在加载类Demo03的时候，只会加载内部类ClassHolder，
    // 但是不会把内部类的属性加载出来
    private static class ClassHolder{
        // 这里执行类加载，是jvm来执行类加载，它一定是单例的，不存在线程安全问题
        // 这里不是调用，是类加载，是成员变量
        private static final HttpHandleManager holder =new HttpHandleManager();

    }

    public static HttpHandleManager of(){//第一次调用getInstance()的时候赋值
        return ClassHolder.holder;
    }

    // 去做请求
    // 用第一个节点/用这条链，去处理请求
    public HttpResponse doRequest(HttpRequest httpRequest){
        return httpHandle.doHttp(httpRequest);
    }


}
