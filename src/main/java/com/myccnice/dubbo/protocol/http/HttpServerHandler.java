package com.myccnice.dubbo.protocol.http;

import java.io.ObjectInputStream;
import java.lang.reflect.Method;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.myccnice.dubbo.framework.Invocation;
import com.myccnice.dubbo.framework.Url;
import com.myccnice.dubbo.register.RegisterCenter;

public class HttpServerHandler {

    public void handle(HttpServletRequest req, HttpServletResponse resp) {
        try {
            ServletInputStream in = req.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            Invocation invocation = (Invocation)ois.readObject();

            Class<?> clazz = RegisterCenter.get(invocation.getInterfaceName(), Url.getLocal());
            Method method = clazz.getMethod(invocation.getMethodName(), invocation.getParamTypes());
            String invoke = (String)method.invoke(clazz.newInstance(), invocation.getParams());

            ServletOutputStream outputStream = resp.getOutputStream();
            IOUtils.write(invoke, outputStream, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
