package com.myccnice.dubbo.register;

import java.util.HashMap;
import java.util.Map;

import com.myccnice.dubbo.framework.Url;

public class RegisterCenter {

    // 服务名称
    private static Map<String, Map<Url, Class<?>>> REGISTER = new HashMap<>();

    public static void register(Class<?> clazz) {
        Map<Url, Class<?>> service = new HashMap<>();
        Url url = new Url("localhost", 8080);
        service.put(url, clazz);
        REGISTER.put(clazz.getName(), service);
    }

    public static Url random(String interfaceName) {
        return REGISTER.get(interfaceName).keySet().iterator().next();
    }

    public static Class<?> get(String interfaceName) {
        Url url = new Url("localhost", 8080);
        return REGISTER.get(interfaceName).get(url);
    }
}
