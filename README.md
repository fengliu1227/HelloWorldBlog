# Hello Word Blog
Developed By Feng(Andrew) Liu
## introduction 
Designed a blog system to practice full stack development, using Spring, Spring MVC, Mybatis, Spring Security, PageHelper plugin, HTML, AJAX, jQuery and CSS, built with RESTful architectural style and managed by MAVEN.

Blog System could create blog to share something interesting, users could talk about technologys, life, career and eveything they want. user could hear back others opinion via comments.

I designed admin in this system, who can delete the comments and blogs to manage the website in a healthy environment, also can block and update the role of other users to admin in the system.


## backend
Database designed with MySQL:

![image](https://github.com/fengliu1227/HelloWorldBlog/blob/master/eer.jpg)

Mapper XML files are created by Mybatis Generator.
### Structure
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
    |_ mybatis<br/>
    |_ mybatis-generator<br/>
    |_ spring<br/>
    |_ springmvc<br/>
    |_ springSecurity<br/>
    |_ dbconfig.properties<br/>
    |_ log4j.properties<br/>

#### service
-BlogService ----> BlogServiceImpl<br/>
-CommentService ----> CommentServiceImpl<br/>
-UserInfoService ----> UserInfoServiceImpl<br/>
## frontend
### Project Structure

#### webapp
     webapp<br/>
      |_ static<br/>
            |_ js<br/>
            |_ image<br/>
            |_ css<br/>
      |_ WEB-INF<br/>
            |_ pages(contains all pages)<br/>
            |_ web.xml<br/>
      |_ index.jsp<br/>
## login page
![image](https://github.com/fengliu1227/HelloWorldBlog/blob/master/login.jpg)

