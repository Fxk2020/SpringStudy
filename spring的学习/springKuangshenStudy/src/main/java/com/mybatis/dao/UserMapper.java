package com.mybatis.dao;

import com.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {


    User findUserByName(String userName);


    @Select(" select * from m_user")
    List<User> getUser();

}
