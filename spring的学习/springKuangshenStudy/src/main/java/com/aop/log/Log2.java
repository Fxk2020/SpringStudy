package com.aop.log;

import org.springframework.aop.AfterAdvice;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class Log2 implements AfterReturningAdvice {

    /**
     *
     * @param o 返回的对象
     * @param method 方法
     * @param objects
     * @param o1 执行的对象
     * @throws Throwable
     */
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        if (o!=null){
            System.out.println(o1.getClass().getName()+"执行了方法"+method.getName()+"返回了"+o.getClass().getName());
        }else {
            System.out.println(o1.getClass().getName()+"执行了方法"+method.getName());
        }

    }
}
