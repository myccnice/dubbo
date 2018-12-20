package com.myccnice.dubbo.consumer;

import com.myccnice.dubbo.facade.HelloService;
import com.myccnice.dubbo.framework.ProxyFactory;

public class Consumer {

    public static void main(String[] args) {
        HelloService service = ProxyFactory.getProxy(HelloService.class);
        String post = service.sayHello("dubbo");
        System.out.println(post);
    }
}