package com.transaction.dao;

import com.transaction.pojo.Blog;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BlogDao implements BlogMapper{

    private SqlSession sqlSession;

    @Value("#{sqlSession}")
    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Blog> selectAllBlogs() {
        return sqlSession.selectList("com.transaction.dao.BlogMapper.selectAllBlogs");
    }

    @Override
    public List<Blog> selectBlogsByUserId(int user_id) {
        return sqlSession.selectList("com.transaction.dao.BlogMapper.selectBlogsByUserId",user_id);
    }
}
