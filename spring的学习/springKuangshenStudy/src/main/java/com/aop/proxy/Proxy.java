package com.aop.proxy;

import com.aop.pojo.Host;
import com.aop.pojo.Rent;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 静态代理
 */
public class Proxy implements Rent {

    private Host host;

    public Proxy() {
    }

    public Proxy(Host host) {
        this.host = host;
    }

    @Override
    public void rent() {

        System.out.println("收取中介费");

        System.out.println("帮助客户看看"+host.getName()+"的房子");

        host.rent();

        System.out.println("签署合同");

    }
}
