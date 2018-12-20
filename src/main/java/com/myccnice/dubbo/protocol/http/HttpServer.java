package com.myccnice.dubbo.protocol.http;

import org.apache.catalina.Context;
import org.apache.catalina.Engine;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

import com.myccnice.dubbo.framework.Url;

/**
 * 启动http服务
 *
 * @author 王鹏
 * @date 2018年12月17日
 */
public class HttpServer {

    /**
<Server port="8005" shutdown="SHUTDOWN">
    <Service name="Catalina">
        <Connector connectionTimeout="20000" port="80" protocol="HTTP/1.1" redirectPort="8443"/>
        <Engine defaultHost="localhost" name="Catalina">
            <Host appBase="webapps" autoDeploy="true" name="localhost" unpackWARs="true">
                <Context docBase="erp-web" path="/" reloadable="true" source="org.eclipse.jst.jee.server:erp-web"/>
            </Host>
        </Engine>
    </Service>
</Server>
     */
    public void start(Url url) {
        Tomcat tomcat = new Tomcat();

        Server server = tomcat.getServer();

        Service service = server.findService("Tomcat");

        Connector connector = new Connector();
        connector.setPort(url.getPort());

        Engine engine = new StandardEngine();
        engine.setDefaultHost(url.getHost());

        Host host = new StandardHost();
        host.setName(url.getHost());

        String path = "";
        Context context = new StandardContext();
        context.setPath(path);
        context.addLifecycleListener(new Tomcat.FixContextListener());

        host.addChild(context);
        engine.addChild(host);
        service.addConnector(connector);
        service.setContainer(engine);
        // 上面是定义一个tomcat
        // tomcat是一个servlet容器，所以tomcat需要一个servlet来处理请求。
        // 我们开发web项目时，web.xml里也是需要这servlet的
        tomcat.addServlet(path, "dispatcher", new DispatcherServlet());
        context.addServletMappingDecoded("/*", "dispatcher");
        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
