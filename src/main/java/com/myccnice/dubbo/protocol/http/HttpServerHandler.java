package com.myccnice.dubbo.protocol.http;

import java.io.ObjectInputStream;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.myccnice.dubbo.framework.Invocation;
import com.myccnice.dubbo.framework.Url;
import com.myccnice.dubbo.register.RegisterCenter;

public class HttpServerHandler {

    public void handle(HttpServletRequest req, HttpServletResponse resp) {
        try {
            ObjectInputStream oos = new ObjectInputStream(req.getInputStream());
            Invocation invocation = (Invocation)oos.readObject();

            Class<?> clazz = RegisterCenter.get(invocation.getInterfaceName(), Url.getLocal());
            Method method = clazz.getMethod(invocation.getMethodName(), invocation.getParamTypes());
            Object invoke = method.invoke(clazz.newInstance(), invocation.getParams());

            IOUtils.write(invoke.toString(), resp.getOutputStream(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
