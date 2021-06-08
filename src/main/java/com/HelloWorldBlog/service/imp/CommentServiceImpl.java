package com.HelloWorldBlog.service.imp;

import com.HelloWorldBlog.bean.Blog;
import com.HelloWorldBlog.bean.BlogExample;
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

    public void deleteById(Integer id){
        commentMapper.deleteByPrimaryKey(id);
    }

    public Comment getById(Integer id){
        return commentMapper.selectByPrimaryKey(id);
    }

    public Integer getIdByAllOtherInfo(Comment comment){
        CommentExample example = new CommentExample();
        example.setDistinct(false);
        CommentExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(comment.getUserId());
        criteria.andBlogIdEqualTo(comment.getBlogId());
        criteria.andContentEqualTo(comment.getContent());
        criteria.andPostTimeEqualTo(comment.getPostTime());
        List<Comment> list = commentMapper.selectByExample(example);
        if(list.size()==1){
            return list.get(0).getId();
        }else{
            return null;
        }
    }

    public void updateComment(Integer id, Comment comment){
        CommentExample example = new CommentExample();
        example.setDistinct(false);
        CommentExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        commentMapper.updateByExampleSelective(comment, example);
    }

    public List<Comment> search(String keyword, Integer id){
        CommentExample example = new CommentExample();
        example.setDistinct(false);
        CommentExample.Criteria criteria = example.createCriteria();
        criteria.andContentLike("%" + keyword + "%");
        criteria.andUserIdEqualTo(id);
        return commentMapper.selectByExample(example);
    }

    public List<Comment> search(String keyword){
        CommentExample example = new CommentExample();
        example.setDistinct(false);
        CommentExample.Criteria criteria = example.createCriteria();
        criteria.andContentLike("%" + keyword + "%");
        return commentMapper.selectByExample(example);
    }
}
