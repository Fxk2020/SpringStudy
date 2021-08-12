package com.mybatis.dao;

import com.mybatis.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl {

    private final UserMapper userMapper;

    public UserMapperImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public int loginVerify(String userName,String password){
        User userByName = userMapper.findUserByName(userName);

        if (userByName==null){
            return -1;//查无此人
        }else {
            if (userByName.getPassword().equals(password)){
                return 0;//密码正确
            }else {
                return 1;//密码错误
            }
        }
    }
}
