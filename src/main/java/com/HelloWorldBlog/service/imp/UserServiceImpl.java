package com.HelloWorldBlog.service.imp;

import com.HelloWorldBlog.bean.UserInfo;
import com.HelloWorldBlog.bean.UserInfoExample;
import com.HelloWorldBlog.dao.UserInfoMapper;
import com.HelloWorldBlog.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;



    public UserInfo getById(Integer id){
        return userInfoMapper.selectByPrimaryKey(id);
    }
    public void addUser(UserInfo userInfo){
        userInfoMapper.insert(userInfo);
    }

    public List<UserInfo> getByEmail(String Email) {

        UserInfoExample example = new UserInfoExample();
        example.setDistinct(false);
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andEmailEqualTo(Email);
        List<UserInfo> list = userInfoMapper.selectByExample(example);
        return list;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfoExample example = new UserInfoExample();
        example.setDistinct(false);
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(s);
        List<UserInfo> list = userInfoMapper.selectByExample(example);
        UserInfo userInfo = list.get(0);
        System.out.println(userInfo);
        User user = new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getStatus()==0, true, true, true, getAuthority(userInfo.getRole()));
        return user;
    }

    private List<SimpleGrantedAuthority> getAuthority(String role){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_" + role));
        return list;
    }
}
