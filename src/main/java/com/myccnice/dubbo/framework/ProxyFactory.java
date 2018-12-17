package com.myccnice.dubbo.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.myccnice.dubbo.protocol.http.HttpClient;
import com.myccnice.dubbo.register.RegisterCenter;

public class ProxyFactory {

    @SuppressWarnings("unchecked")
    public static <T> T getProxy(Class<T> clazz) {
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, new InvocationHandler() {
            @Override
            public String invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Invocation invocation = new Invocation(clazz.getName(), method.getName(), args, method.getParameterTypes());
                Url url = RegisterCenter.random(clazz.getName());
                return HttpClient.post(url.getHost(), url.getPort(), invocation );
            }
        });
    }
}
