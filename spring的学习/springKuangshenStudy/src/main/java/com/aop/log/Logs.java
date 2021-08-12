package com.aop.log;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logs {

    @Before("execution(* com.aop.dao.UserDaoImpl.*(..))")
    public void before(){
        System.out.println("=======方法执行之前=======");
    }

    @After("execution(* com.aop.dao.UserDaoImpl.*(..))")
    public void after(){
        System.out.println("=======方法执行之后=======");
    }

    @Around("execution(* com.aop.dao.UserDaoImpl.*(..))")
    public void around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("环绕前");
        point.proceed();
        System.out.println("环绕后");
    }


}
