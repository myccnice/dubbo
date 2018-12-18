package com.myccnice.dubbo.provider;

import com.myccnice.dubbo.framework.Url;
import com.myccnice.dubbo.protocol.Protocol;
import com.myccnice.dubbo.protocol.ProtocolFactory;
import com.myccnice.dubbo.provider.service.HelloService;
import com.myccnice.dubbo.provider.service.impl.HelloServiceImpl;
import com.myccnice.dubbo.register.RegisterCenter;

public class Provider {

    public static void main(String[] args) {
        // 服务注册
        Url url = new Url("localhost", 8080);
        RegisterCenter.register(
                HelloService.class.getName(),
                url,
                HelloServiceImpl.class);
        // 服务暴露
        Protocol protocol = ProtocolFactory.get();
        protocol.start(url);
    }
}
