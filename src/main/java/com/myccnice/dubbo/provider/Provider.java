package com.myccnice.dubbo.provider;

import com.myccnice.dubbo.framework.Url;
import com.myccnice.dubbo.protocol.http.HttpServer;
import com.myccnice.dubbo.provider.service.impl.HelloServiceImpl;
import com.myccnice.dubbo.register.RegisterCenter;

public class Provider {

    public static void main(String[] args) {
        // 服务注册
        RegisterCenter.register(HelloServiceImpl.class);
        // 服务暴露
        Url url = new Url("localhost", 8080);
        new HttpServer().start(url);
    }
}
