package com.myccnice.dubbo.register;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.myccnice.dubbo.framework.Url;

public class RegisterCenter {

    // {服务名:{URL:实现类}}
    private static Map<String, Map<Url, Class<?>>> REGISTER = new HashMap<>();
    private static final String FILE_NAME = "E:/dubbo.cache";

    /**
     * 服务注册
     * @param intefaceName 服务名
     * @param url 访问地址
     * @param impl 实现类
     */
    public static void register(String intefaceName, Url url, Class<?> impl) {
        Map<Url, Class<?>> service = new HashMap<>();
        service.put(url, impl);
        REGISTER.put(intefaceName, service);

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(REGISTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 模拟软负载均衡
     * @param interfaceName 服务名称
     * @return 访问地址
     */
    @SuppressWarnings("unchecked")
    public static Url random(String interfaceName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));){
            REGISTER = (Map<String, Map<Url, Class<?>>>)ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return REGISTER.get(interfaceName).keySet().iterator().next();
    }

    public static Class<?> get(String interfaceName, Url url) {
        return REGISTER.get(interfaceName).get(url);
    }
}
