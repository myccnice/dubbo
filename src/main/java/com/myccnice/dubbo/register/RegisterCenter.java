package com.myccnice.dubbo.register;

import java.util.HashMap;
import java.util.Map;

import com.myccnice.dubbo.framework.Url;

public class RegisterCenter {

    // {服务名:{URL:实现类}}
    private static Map<String, Map<Url, Class<?>>> REGISTER = new HashMap<>();

    public static void register(String intefaceName, Url url, Class<?> impl) {
        Map<Url, Class<?>> service = new HashMap<>();
        service.put(url, impl);
        REGISTER.put(intefaceName, service);
    }

    public static Url random(String interfaceName) {
        return REGISTER.get(interfaceName).keySet().iterator().next();
    }

    public static Class<?> get(String interfaceName, Url url) {
        return REGISTER.get(interfaceName).get(url);
    }
}
