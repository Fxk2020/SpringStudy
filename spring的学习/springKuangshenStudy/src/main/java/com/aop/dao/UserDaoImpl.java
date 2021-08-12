package com.aop.dao;

public class UserDaoImpl implements UserDao {
    @Override
    public void add() {
        System.out.println("add");
    }

    @Override
    public void delete() {
        System.out.println("delete");
    }

    @Override
    public void update() {
        System.out.println("update");
    }

    @Override
    public void search() {
        System.out.println("search");
    }
}
