package com.myccnice.dubbo.provider.service.impl;

import com.myccnice.dubbo.facade.HelloService;

public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String username) {
        return "hello " + username;
    }

}
