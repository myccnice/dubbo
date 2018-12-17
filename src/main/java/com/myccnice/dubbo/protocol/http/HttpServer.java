package com.myccnice.dubbo.protocol.http;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.startup.Tomcat;

import com.myccnice.dubbo.framework.Url;

/**
 * 启动http服务
 *
 * @author 王鹏
 * @date 2018年12月17日
 */
public class HttpServer {

    public void start(Url url) {
        Tomcat tomcat = new Tomcat();
        Server server = tomcat.getServer();
        try {
            tomcat.addServlet("", "servletDispatcher", new ServletDispatcher());
            tomcat.start();
            server.await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
