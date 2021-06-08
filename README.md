# Hello Word Blog
Developed By Feng(Andrew) Liu
## introduction 
Designed a blog system to practice full stack development, using Spring, Spring MVC, Mybatis, Spring Security, PageHelper plugin, HTML, AJAX, jQuery and CSS, built with RESTful architectural style and managed by MAVEN.

Blog System could create blog to share something interesting, users could talk about technologys, life, career and eveything they want. user could hear back others opinion via comments.

I designed admin in this system, who can delete the comments and blogs to manage the website in a healthy environment, also can block and update the role of other users to admin in the system.

## How to use

Download this .zip file and open with your IDE, configure the Tomcat server, after register just enjoy yourself
 
## backend
Database designed with MySQL:

![image](https://github.com/fengliu1227/HelloWorldBlog/blob/master/eer.jpg)

Mapper XML files are created by Mybatis Generator.
### Structure
```bash
|_ java<br/>
    |_ com.HelloWordBlog<br/>
                  |_ bean<br/>
                        |_ static<br/>
                        |_ Blog<br/>
                        |_ BlogExample<br/>
                        |_ Comment<br/>
                        |_ CommentExample<br/>
                        |_ UserInfo<br/>
                        |_ UserInfoExample<br/>
                   |_ dao<br/>
                        |_ static<br/>
                        |_ BlogMapper<br/>
                        |_ CommentMapper<br/>
                        |_ UserMapper<br/>
                   |_ controller<br/>
                        |_ AdminController<br/>
                        |_ UserController<br/>
                        |_ BlogController<br/>
                        |_ CommentController<br/>
                        |_ SystemController<br/>
                   |_ service<br/>
                        |_ UserInfoService<br/>
                        |_ BlogService<br/>
                        |_ CommentService<br/>
                        |_ imp<br/>
                              |_ UserInfoServiceImpl<br/>
                              |_ BlogServiceImpl<br/>
                              |_ CommentServiceImpl<br/>
|_ sources<br/>
    |_ mapper<br/>
          |_ UserInfoMapper.xml<br/>
          |_ BlogMapper.xml<br/>
          |_ CommentInfoMapper.xml<br/>
    |_ mybatis<br/>
          |_mybatis-config.xml
    |_ mybatis-generator<br/>
          |_generatorConfig.xml
    |_ spring<br/>
          |_applicationContext.xml
    |_ springmvc<br/>
          |_springmvc.xml
    |_ springSecurity<br/>
          |_spring-security.xml
    |_ dbconfig.properties<br/>
    |_ log4j.properties<br/>
```

## frontend
### Project Structure
```bash
     webapp<br/>
      |_ static<br/>
            |_ js<br/>
            |_ image<br/>
            |_ css<br/>
      |_ WEB-INF<br/>
            |_ pages(contains all pages)<br/>
                 |_userDetail.jsp
                 |_commentResult.jsp
                 |_editBlog.jsp
                 |_error.jsp
                 |_blog.jsp
                 |_profile.jsp
                  |_adminMain.jsp
                 |_login.jsp
                 |_userResult.jsp
                 |_searchResult.jsp
                 |_blogDetail.jsp
            |_ web.xml<br/>
      |_ index.jsp<br/>
```

Perfected website hierarchy using HTML, AJAX, jQuery, PageHelper plugin and CSS for ease of use and reflecting changes from user update in real time and user friendliness

Managed consistence of code version and process of project through GitHub

## login page
![image](https://github.com/fengliu1227/HelloWorldBlog/blob/master/login.jpg)


Still updating for the frontend view and some function to enhance user friendliness.
