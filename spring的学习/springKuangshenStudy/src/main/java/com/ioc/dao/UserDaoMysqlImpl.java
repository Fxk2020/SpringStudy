package com.ioc.dao;

public class UserDaoMysqlImpl implements UserDao{
    @Override
    public void getUser() {
        System.out.println("Mysql的实现逻辑");
    }
}
