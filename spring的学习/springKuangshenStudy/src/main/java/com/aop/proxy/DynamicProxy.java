package com.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.SQLOutput;

/**
 * 动态代理
 */
public class DynamicProxy implements InvocationHandler {

    //要代理的对象
    private Object object;

    public DynamicProxy(Object object) {
        this.object = object;
    }

    //代理需要额外做的事情
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始事物"+method.getName());

        Object invoke = method.invoke(object, args);

        System.out.println("结束事物"+method.getName());

        return invoke;
    }

    //生成代理类
    public Object getProxy(){
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),this);
    }

}
