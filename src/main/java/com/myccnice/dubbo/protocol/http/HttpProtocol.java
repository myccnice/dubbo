package com.myccnice.dubbo.protocol.http;

import com.myccnice.dubbo.framework.Invocation;
import com.myccnice.dubbo.framework.Url;
import com.myccnice.dubbo.protocol.Protocol;

public class HttpProtocol implements Protocol {

    @Override
    public void start(Url url) {
        new HttpServer().start(url);
    }

    @Override
    public String send(Url url, Invocation invocation) {
        return HttpClient.post(url.getHost(), url.getPort(), invocation);
    }

}
