package com.myccnice.dubbo.protocol;

import com.myccnice.dubbo.protocol.dubbo.DubboProtocol;
import com.myccnice.dubbo.protocol.http.HttpProtocol;

public class ProtocolFactory {

    public static Protocol get() {
        String property = System.getProperty("protocol");
        if (property == null) {
            return new HttpProtocol();
        }
        switch (property) {
        case "dubbo":
            return new DubboProtocol();
        default:
            return new HttpProtocol();
        }
    }
}
