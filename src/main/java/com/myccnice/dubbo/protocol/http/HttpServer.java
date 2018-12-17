package com.myccnice.dubbo.protocol.http;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Server;
import org.apache.catalina.Service;
import org.apache.catalina.core.StandardContext;
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
        try {
            Context context = new StandardContext();
            tomcat.addServlet("", "servletDispatcher", new ServletDispatcher());
            context.addServletMappingDecoded("/*", "servletDispatcher");
            tomcat.start();
            server.await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
