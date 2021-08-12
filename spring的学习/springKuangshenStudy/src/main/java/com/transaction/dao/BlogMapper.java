package com.transaction.dao;

import com.transaction.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface BlogMapper {

    @Select("select * from m_blog")
    List<Blog> selectAllBlogs();

    @Select("select * from m_blog where user_id=${user_id}")
    List<Blog> selectBlogsByUserId(@Param("user_id") int user_id);
}
