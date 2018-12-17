package com.myccnice.dubbo.framework;

/**
 * 用于传递请求参数
 *
 * @author 王鹏
 * @date 2018年12月17日
 */
public class Invocation {

    /**
     * 服务名
     */
    private String interfaceName;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 请求参数
     */
    private Object[] params;

    /**
     * 参数类型
     */
    private Class<?>[] paramTypes;

    public Invocation(String interfaceName, String methodName, Object[] params, Class<?>[] paramTypes) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.params = params;
        this.paramTypes = paramTypes;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class<?>[] paramTypes) {
        this.paramTypes = paramTypes;
    }
}
