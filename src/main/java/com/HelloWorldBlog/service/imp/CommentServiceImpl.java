package com.HelloWorldBlog.service.imp;

import com.HelloWorldBlog.bean.Comment;
import com.HelloWorldBlog.bean.CommentExample;
import com.HelloWorldBlog.dao.CommentMapper;
import com.HelloWorldBlog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public List<Comment> getByUserId(Integer userId){
        CommentExample example = new CommentExample();
        example.setDistinct(false);
        CommentExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<Comment> list = commentMapper.selectByExample(example);
        return list;
    }

    public List<Comment> getByBlogId(Integer blogId){
        CommentExample example = new CommentExample();
        example.setDistinct(false);
        CommentExample.Criteria criteria = example.createCriteria();
        criteria.andBlogIdEqualTo(blogId);
        List<Comment> list = commentMapper.selectByExample(example);
        return list;
    }

    public void insert(Comment comment){
        commentMapper.insert(comment);
    }
}
