package com.HelloWorldBlog.service.imp;

import com.HelloWorldBlog.bean.User;
import com.HelloWorldBlog.bean.UserExample;
import com.HelloWorldBlog.dao.UserMapper;
import com.HelloWorldBlog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;



    public User getById(Integer id){
        return userMapper.selectByPrimaryKey(id);
    }
    public void addUser(User user){
        userMapper.insert(user);
    }

    public List<User> getByEmail(String Email) {

        UserExample example = new UserExample();
        example.setDistinct(false);
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andEmailEqualTo(Email);
        List<User> list = userMapper.selectByExample(example);
        return list;
    }
}
