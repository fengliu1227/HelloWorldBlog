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

    public Integer getIdByAllOtherInfo(Blog blog){
        BlogExample example = new BlogExample();
        example.setDistinct(false);
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(blog.getUserId());
        criteria.andContentEqualTo(blog.getContent());
        criteria.andCreateTimeEqualTo(blog.getCreateTime());
        List<Blog> list = blogMapper.selectByExample(example);
        if(list.size()==1){
            return list.get(0).getId();
        }else{
            return null;
        }
    }

    public void updateBlog(Integer id, Blog blog){
        BlogExample example = new BlogExample();
        example.setDistinct(false);
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        blogMapper.updateByExampleSelective(blog, example);
    }

    public void deleteById(Integer id){
        blogMapper.deleteByPrimaryKey(id);
    }
}
