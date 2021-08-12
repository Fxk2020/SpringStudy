package com.mybatis.dao;

import com.mybatis.pojo.User;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserDao implements UserMapper{

    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public User findUserByName(String userName) {
        return sqlSession.selectOne("com.mybatis.dao.UserMapper.findUserByName",userName);
    }

    @Override
    public List<User> getUser() {
        return sqlSession.selectList("com.mybatis.dao.UserMapper.getUser");

    }
}
