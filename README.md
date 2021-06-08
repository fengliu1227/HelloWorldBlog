# Hello Word Blog
Developed By Feng(Andrew) Liu
## introduction 
Designed a blog system to practice full stack development, using Spring, Spring MVC, Mybatis, Spring Security, PageHelper plugin, HTML, AJAX, jQuery and CSS, built with RESTful architectural style and managed by MAVEN.

Blog System could create blog to share something interesting, users could talk about technologys, life, career and eveything they want. user could hear back others opinion via comments.

I designed admin in this system, who can delete the comments and blogs to manage the website in a healthy environment, also can block and update the role of other users to admin in the system.

## Tree Structure
.<br/>
|_target<br/>
| |_generated-sources<br/>
| | |_annotations<br/>
| |_classes<br/>
| | |_dbconfig.properties<br/>
| | |_mybatis<br/>
| | | |_mybatis-config.xml<br/>
| | |_mapper<br/>
| | | |_CommentMapper.xml<br/>
| | | |_BlogMapper.xml<br/>
| | | |_UserInfoMapper.xml<br/>
| | |_springSecurity<br/>
| | | |_spring-security.xml<br/>
| | |_mybatis-generator<br/>
| | | |_generatorConfig.xml<br/>
| | |_com<br/>
| | | |_HelloWorldBlog<br/>
| | | | |_bean<br/>
| | | | | |_UserInfoExample$GeneratedCriteria.class<br/>
| | | | | |_BlogExample$Criterion.class<br/>
| | | | | |_BlogExample$Criteria.class<br/>
| | | | | |_UserInfo.class<br/>
| | | | | |_CommentExample.class<br/>
| | | | | |_Blog.class<br/>
| | | | | |_Comment.class<br/>
| | | | | |_BlogExample.class<br/>
| | | | | |_BlogExample$GeneratedCriteria.class<br/>
| | | | | |_UserInfoExample$Criterion.class<br/>
| | | | | |_UserInfoExample.class<br/>
| | | | | |_CommentExample$GeneratedCriteria.class<br/>
| | | | | |_CommentExample$Criterion.class<br/>
| | | | | |_CommentExample$Criteria.class<br/>
| | | | | |_UserInfoExample$Criteria.class<br/>
| | | | |_dao<br/>
| | | | | |_CommentMapper.class<br/>
| | | | | |_UserInfoMapper.class<br/>
| | | | | |_BlogMapper.class<br/>
| | | | |_controller<br/>
| | | | | |_SystemController.class<br/>
| | | | | |_BlogController.class<br/>
| | | | | |_CommentController.class<br/>
| | | | | |_AdminController.class<br/>
| | | | | |_UserController.class<br/>
| | | | |_service<br/>
| | | | | |_UserInfoService.class<br/>
| | | | | |_imp<br/>
| | | | | | |_CommentServiceImpl.class<br/>
| | | | | | |_UserServiceImpl.class<br/>
| | | | | | |_BlogServiceImpl.class<br/>
| | | | | |_BlogService.class<br/>
| | | | | |_CommentService.class<br/>
| | |_log4j.properties<br/>
| | |_spring<br/>
| | | |_applicationContext.xml<br/>
| | |_springmvc<br/>
| | | |_springmvc.xml<br/>
| |_HelloWorldBlog<br/>
| | |_META-INF<br/>
| | |_static<br/>
| | | |_css<br/>
| | | | |_style.css<br/>
| | | |_images<br/>
| | | | |_login-background.jpg<br/>
| | | |_js<br/>
| | | | |_date.format.js<br/>
| | |_index.jsp<br/>
| | |_WEB-INF<br/>
| | | |_classes<br/>
| | | | | |_HelloWorldBlog<br/>
| | | | | | |_bean<br/>
| | | | | | |_dao<br/>
| | | | | | |_controller<br/>
| | | | | | |_service<br/>
| | | | | | | |_imp<br/>
| | | | |_log4j.properties<br/>
| | | | |_spring<br/>
| | | | | |_applicationContext.xml<br/>
| | | | |_springmvc<br/>
| | | | | |_springmvc.xml<br/>
| | | |_lib<br/>
| | | |_web.xml<br/>
| | | |_pages<br/>
| | | | |_userDetail.jsp<br/>
| | | | |_commentResult.jsp<br/>
| | | | |_editBlog.jsp<br/>
| | | | |_error.jsp<br/>
| | | | |_blog.jsp<br/>
| | | | |_profile.jsp<br/>
| | | | |_adminMain.jsp<br/>
| | | | |_login.jsp<br/>
| | | | |_userResult.jsp<br/>
| | | | |_searchResult.jsp<br/>
| | | | |_blogDetail.jsp<br/>
|_pom.xml<br/>

## backend
Database designed with MySQL:

![image](https://github.com/fengliu1227/HelloWorldBlog/blob/master/eer.jpg)

Mapper XML files are created by Mybatis Generator.


## frontend
## login page
![image](https://github.com/fengliu1227/HelloWorldBlog/blob/master/login.jpg)

