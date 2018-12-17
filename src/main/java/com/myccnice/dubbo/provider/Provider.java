package com.myccnice.dubbo.provider;

import com.myccnice.dubbo.framework.Url;
import com.myccnice.dubbo.protocol.Protocol;
import com.myccnice.dubbo.protocol.ProtocolFactory;
import com.myccnice.dubbo.provider.service.HelloService;
import com.myccnice.dubbo.provider.service.impl.HelloServiceImpl;
import com.myccnice.dubbo.register.RegisterCenter;

public class Provider {

    public static void main(String[] args) {
        HelloService service = new HelloServiceImpl();
        // 服务注册
        RegisterCenter.register(service.getClass());
        // 服务暴露
        Url url = new Url("localhost", 8080);
        Protocol protocol = ProtocolFactory.get();
        protocol.start(url);
    }
}
