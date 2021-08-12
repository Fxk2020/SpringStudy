package com.ioc.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//相当于创建了一个bean
@Component
//作用域的问题  默认是singleton
@Scope("prototype")
public class User {

    @Value("fxk")
    private String userName;
    @Value("123456")
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
