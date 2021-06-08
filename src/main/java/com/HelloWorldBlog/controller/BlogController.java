package com.HelloWorldBlog.controller;


import com.HelloWorldBlog.bean.Blog;
import com.HelloWorldBlog.bean.Comment;
import com.HelloWorldBlog.bean.UserInfo;
import com.HelloWorldBlog.service.BlogService;
import com.HelloWorldBlog.service.CommentService;
import com.HelloWorldBlog.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    CommentService commentService;

    //home page
    @RequestMapping("/blog")
    public String getBlogs(@RequestParam(value="pn",defaultValue="1")Integer pn, Model model, HttpSession httpSession){
        PageHelper.startPage(pn, 6);
        Collection<Blog> blogs = blogService.getAll();
        PageInfo pageInfo = new PageInfo((List) blogs, 5);
//      PageInfo contains a very comprehensive paging property
//        assertEquals(1, page.getPageNum());
//        assertEquals(10, page.getPageSize());
//        assertEquals(1, page.getStartRow());
//        assertEquals(10, page.getEndRow());
//        assertEquals(183, page.getTotal());
//        assertEquals(19, page.getPages());
//        assertEquals(1, page.getFirstPage());
//        assertEquals(8, page.getLastPage());
//        assertEquals(true, page.isFirstPage());
//        assertEquals(false, page.isLastPage());
//        assertEquals(false, page.isHasPreviousPage());
//        assertEquals(true, page.isHasNextPage());
        model.addAttribute("blogs", blogs);
        model.addAttribute("pageInfo", pageInfo);
        String userName = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
        List<UserInfo> list = userInfoService.getByUsername(userName);
        Integer userId = list.get(0).getId();
        model.addAttribute("userInfo", list.get(0));
        httpSession.setAttribute("userId", userId);
        httpSession.setAttribute("userName", userName);
        return "blog";
    }

    @RequestMapping(value="/blog/{id}", method= RequestMethod.GET)
    public String getBlogById(@PathVariable("id") Integer id, Model model) {
        Blog blog = blogService.getBlogById(id);
        model.addAttribute("blog", blog);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createDateStr = formatter.format(blog.getCreateTime());

        List<Comment> comments = commentService.getByBlogId(id);
        model.addAttribute("RelatedComments", comments);
        model.addAttribute("createDateStr", createDateStr);
        String userName = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
        model.addAttribute("viewer", userName);

        return "blogDetail";
    }

    //put the data in the response body, if it is an object, put it in JSON
    @ResponseBody
    @RequestMapping(value="/blog", method=RequestMethod.POST)
    public Blog addBlog(@RequestBody Blog blog, HttpSession httpSession) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = formatter.format(new Date(System.currentTimeMillis()));
        Date createDate = formatter.parse(now);
        blog.setCreateTime(createDate);
        if(httpSession.getAttribute("userId") != null && httpSession.getAttribute("userName") != null){
            Integer userId = Integer.parseInt(httpSession.getAttribute("userId").toString());
            String userName = httpSession.getAttribute("userName").toString();
            blog.setUserName(userName);
            blog.setUserId(userId);
        }else{
            String userName = ((UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal())
                    .getUsername();
            List<UserInfo> list = userInfoService.getByUsername(userName);
            Integer userId = list.get(0).getId();
            blog.setUserName(userName);
            blog.setUserId(userId);
            httpSession.setAttribute("userId", userId);
            httpSession.setAttribute("userName", userName);
        }
        blogService.insertBlog(blog);
        blog.setId(blogService.getIdByAllOtherInfo(blog));

        return blog;
    }

    @RequestMapping(value="/blog/search")
    public String search(@RequestParam(value="pn",defaultValue="1")Integer pn, @RequestParam(value="keyword")String keyword, Model model){
        PageHelper.startPage(pn, 6);
        List<Blog> blogs = blogService.search(keyword);
        PageInfo pageInfo = new PageInfo(blogs, 5);
        model.addAttribute("blogs", blogs);
        model.addAttribute("pageInfo", pageInfo);
        String userName = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
        List<UserInfo> list = userInfoService.getByUsername(userName);
        Integer userId = list.get(0).getId();
        model.addAttribute("userInfo", list.get(0));
        return "blog";
    }

    @RequestMapping(value="/blog/search-this-user")
    public String searchThisUser(@RequestParam(value="pn",defaultValue="1")Integer pn, @RequestParam(value="keyword")String keyword, Model model){
        String userName = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal()).
                getUsername();
        List<UserInfo> list = userInfoService.getByUsername(userName);
        Integer userId = list.get(0).getId();
        PageHelper.startPage(pn, 6);
        List<Blog> blogs = blogService.searchThisUser(keyword, userId);
        PageInfo pageInfo = new PageInfo(blogs, 5);
        model.addAttribute("blogs", blogs);
        model.addAttribute("pageInfo", pageInfo);
        return "searchResult";
    }

    @RequestMapping(value="/blog/search/admin")
    public String searchByAdmin(@RequestParam(value="pn",defaultValue="1")Integer pn, @RequestParam(value="keyword")String keyword, Model model){
        String userName = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal()).
                getUsername();
        List<UserInfo> list = userInfoService.getByUsername(userName);
        UserInfo userInfo = list.get(0);
        PageHelper.startPage(pn, 6);
        List<Blog> blogs = blogService.search(keyword);
        PageInfo pageInfo = new PageInfo(blogs, 5);
        model.addAttribute("blogs", blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("userInfo", userInfo);
        return "searchResult";
    }

    @RequestMapping(value="/editblog/{id}", method= RequestMethod.GET)
    public String editBlog(@PathVariable("id") Integer id, Model model) {
        Blog blog = blogService.getBlogById(id);
        String userName = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
        if(blog.getUserName().equals(userName)){
            model.addAttribute("blog", blog);
            return "editBlog";
        }else{
            model.addAttribute("error", "You don't have permission to do that!!!");
            return "error";
        }
    }

    @RequestMapping(value="/blog/{id}", method= RequestMethod.PUT)
    public String editSubmit(@PathVariable("id")Integer id, Blog blog) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = formatter.format(new Date(System.currentTimeMillis()));
        Date updateTime = formatter.parse(now);
        blog.setUpdateTime(updateTime);
        System.out.println(blog);
        blogService.updateBlog(id, blog);
        return "redirect:/blog/"+id;
    }

    @ResponseBody
    @RequestMapping(value="/blog/{id}", method= RequestMethod.DELETE)
    public Integer deleteBlog(@PathVariable("id")Integer id, Model model){
        String userName = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
        Blog blog = blogService.getBlogById(id);
        List<UserInfo> list = userInfoService.getByUsername(userName);
        UserInfo userInfo = list.get(0);
        if(blog.getUserName().equals(userName) || userInfo.getRole().equals("ADMIN")){
            blogService.deleteById(id);
            return id;
        }else{
            return null;
        }
    }
}
