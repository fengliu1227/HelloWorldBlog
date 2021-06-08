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
    |_ mybatis-generator<br/>
    |_ spring<br/>
    |_ springmvc<br/>
    |_ springSecurity<br/>
    |_ dbconfig.properties<br/>
    |_ log4j.properties<br/>
```
```bash
.
|_target
| |_generated-sources
| | |_annotations
| |_classes
| | |_dbconfig.properties
| | |_mybatis
| | | |_mybatis-config.xml
| | |_mapper
| | | |_CommentMapper.xml
| | | |_BlogMapper.xml
| | | |_UserInfoMapper.xml
| | |_springSecurity
| | | |_spring-security.xml
| | |_mybatis-generator
| | | |_generatorConfig.xml
| | |_com
| | | |_HelloWorldBlog
| | | | |_test
| | | | | |_mybatisTest.class
| | | | |_bean
| | | | | |_UserInfoExample$GeneratedCriteria.class
| | | | | |_BlogExample$Criterion.class
| | | | | |_BlogExample$Criteria.class
| | | | | |_UserInfo.class
| | | | | |_CommentExample.class
| | | | | |_Blog.class
| | | | | |_Comment.class
| | | | | |_BlogExample.class
| | | | | |_BlogExample$GeneratedCriteria.class
| | | | | |_UserInfoExample$Criterion.class
| | | | | |_UserInfoExample.class
| | | | | |_CommentExample$GeneratedCriteria.class
| | | | | |_CommentExample$Criterion.class
| | | | | |_CommentExample$Criteria.class
| | | | | |_UserInfoExample$Criteria.class
| | | | |_dao
| | | | | |_CommentMapper.class
| | | | | |_UserInfoMapper.class
| | | | | |_BlogMapper.class
| | | | |_controller
| | | | | |_SystemController.class
| | | | | |_BlogController.class
| | | | | |_CommentController.class
| | | | | |_AdminController.class
| | | | | |_UserController.class
| | | | |_service
| | | | | |_UserInfoService.class
| | | | | |_imp
| | | | | | |_CommentServiceImpl.class
| | | | | | |_UserServiceImpl.class
| | | | | | |_BlogServiceImpl.class
| | | | | |_BlogService.class
| | | | | |_CommentService.class
| | |_log4j.properties
| | |_spring
| | | |_applicationContext.xml
| | |_springmvc
| | | |_springmvc.xml
| |_HelloWorldBlog
| | |_META-INF
| | | |_MANIFEST.MF
| | |_static
| | | |_css
| | | | |_style.css
| | | |_images
| | | | |_login-background.jpg
| | | |_js
| | | | |_date.format.js
| | |_index.jsp
| | |_WEB-INF
| | | |_classes
| | | | |_dbconfig.properties
| | | | |_mybatis
| | | | | |_mybatis-config.xml
| | | | |_mapper
| | | | | |_CommentMapper.xml
| | | | | |_BlogMapper.xml
| | | | | |_UserInfoMapper.xml
| | | | |_springSecurity
| | | | | |_spring-security.xml
| | | | |_mybatis-generator
| | | | | |_generatorConfig.xml
| | | | |_com
| | | | | |_HelloWorldBlog
| | | | | | |_test
| | | | | | | |_mybatisTest.class
| | | | | | |_bean
| | | | | | | |_UserInfoExample$GeneratedCriteria.class
| | | | | | | |_BlogExample$Criterion.class
| | | | | | | |_BlogExample$Criteria.class
| | | | | | | |_UserInfo.class
| | | | | | | |_CommentExample.class
| | | | | | | |_Blog.class
| | | | | | | |_Comment.class
| | | | | | | |_BlogExample.class
| | | | | | | |_BlogExample$GeneratedCriteria.class
| | | | | | | |_UserInfoExample$Criterion.class
| | | | | | | |_UserInfoExample.class
| | | | | | | |_CommentExample$GeneratedCriteria.class
| | | | | | | |_CommentExample$Criterion.class
| | | | | | | |_CommentExample$Criteria.class
| | | | | | | |_UserInfoExample$Criteria.class
| | | | | | |_dao
| | | | | | | |_CommentMapper.class
| | | | | | | |_UserInfoMapper.class
| | | | | | | |_BlogMapper.class
| | | | | | |_controller
| | | | | | | |_SystemController.class
| | | | | | | |_BlogController.class
| | | | | | | |_CommentController.class
| | | | | | | |_AdminController.class
| | | | | | | |_UserController.class
| | | | | | |_service
| | | | | | | |_UserInfoService.class
| | | | | | | |_imp
| | | | | | | | |_CommentServiceImpl.class
| | | | | | | | |_UserServiceImpl.class
| | | | | | | | |_BlogServiceImpl.class
| | | | | | | |_BlogService.class
| | | | | | | |_CommentService.class
| | | | |_log4j.properties
| | | | |_spring
| | | | | |_applicationContext.xml
| | | | |_springmvc
| | | | | |_springmvc.xml
| | | |_lib
| | | | |_slf4j-api-1.6.6.jar
| | | | |_c3p0-0.9.2.jar
| | | | |_mysql-connector-java-8.0.20.jar
| | | | |_jsqlparser-3.2.jar
| | | | |_spring-context-5.0.2.RELEASE.jar
| | | | |_protobuf-java-3.6.1.jar
| | | | |_mchange-commons-java-0.2.3.4.jar
| | | | |_spring-web-5.0.2.RELEASE.jar
| | | | |_jstl-1.2.jar
| | | | |_mybatis-3.4.1.jar
| | | | |_junit-4.12.jar
| | | | |_jackson-core-2.10.0.jar
| | | | |_spring-expression-5.0.2.RELEASE.jar
| | | | |_spring-webmvc-5.0.2.RELEASE.jar
| | | | |_spring-aop-5.0.2.RELEASE.jar
| | | | |_log4j-1.2.12.jar
| | | | |_spring-test-5.0.2.RELEASE.jar
| | | | |_slf4j-log4j12-1.6.6.jar
| | | | |_spring-security-web-5.0.2.RELEASE.jar
| | | | |_spring-security-config-5.0.2.RELEASE.jar
| | | | |_spring-core-5.0.2.RELEASE.jar
| | | | |_mybatis-spring-1.3.0.jar
| | | | |_spring-beans-5.0.2.RELEASE.jar
| | | | |_hamcrest-core-1.3.jar
| | | | |_spring-jcl-5.0.2.RELEASE.jar
| | | | |_spring-tx-5.0.2.RELEASE.jar
| | | | |_spring-security-core-5.0.2.RELEASE.jar
| | | | |_pagehelper-5.2.0.jar
| | | | |_spring-jdbc-5.0.2.RELEASE.jar
| | | | |_aspectjweaver-1.6.8.jar
| | | | |_jackson-annotations-2.10.0.jar
| | | | |_jackson-databind-2.10.0.jar
| | | |_web.xml
| | | |_pages
| | | | |_userDetail.jsp
| | | | |_commentResult.jsp
| | | | |_editBlog.jsp
| | | | |_error.jsp
| | | | |_blog.jsp
| | | | |_profile.jsp
| | | | |_adminMain.jsp
| | | | |_login.jsp
| | | | |_userResult.jsp
| | | | |_searchResult.jsp
| | | | |_blogDetail.jsp
|_pom.xml
|_README.md
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
            |_ web.xml<br/>
      |_ index.jsp<br/>
```
## login page
![image](https://github.com/fengliu1227/HelloWorldBlog/blob/master/login.jpg)

