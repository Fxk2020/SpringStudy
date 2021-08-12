package com.ioc.service;

import com.ioc.dao.UserDao;
import com.ioc.dao.UserDaoImpl;
import com.ioc.dao.UserDaoMysqlImpl;

public class UserServiceImpl implements UserService{

//    UserDao userDao = new UserDaoImpl();
//    UserDao userDao = new UserDaoMysqlImpl();//逻辑层代码的修改导致业务层代码修改

    UserDao userDao;

    //通过set方法动态赋值，注入不同的值，可以适应用户不同的请求，不需要修改源代码
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void getUser() {
        userDao.getUser();
    }
}
