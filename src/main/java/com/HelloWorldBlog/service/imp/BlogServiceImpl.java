package com.HelloWorldBlog.service.imp;

import com.HelloWorldBlog.bean.Blog;
import com.HelloWorldBlog.bean.BlogExample;
import com.HelloWorldBlog.dao.BlogMapper;
import com.HelloWorldBlog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    public List<Blog> getAll() {
        BlogExample blogExample = new BlogExample();
        blogExample.setOrderByClause("id desc");
        return blogMapper.selectByExample(blogExample);
    }

    public List<Blog> getByUserId(Integer userId){
        BlogExample example = new BlogExample();
        example.setDistinct(false);
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<Blog> list = blogMapper.selectByExample(example);
        return list;
    }

    public Blog getBlogById(Integer id) {
        return blogMapper.selectByPrimaryKey(id);
    }

    public void insertBlog(Blog blog){
        blogMapper.insert(blog);
    }
}
