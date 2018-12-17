package com.myccnice.dubbo.protocol;

import com.myccnice.dubbo.framework.Invocation;
import com.myccnice.dubbo.framework.Url;

public interface Protocol {

    void start(Url url);
    
    String send(Url url, Invocation invocation);
}
