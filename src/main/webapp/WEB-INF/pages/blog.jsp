<%--
  Created by IntelliJ IDEA.
  User: fengliu
  Date: 4/22/21
  Time: 6:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <img src="/images/main.jpg"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
    <% pageContext.setAttribute("ctp", request.getContextPath()); %>
</head>
<nav>
    <a href="${ctp}/user">Profile</a>
    <a href="${ctp}/logout.do">Logout</a>
    <a href="${ctp}/admin">Admin!</a>
</nav>
<body>


<div id="addBlog">
    <form action="${ctp}/addBlog" method="POST">
        Title:<input id="add-blog-title" type="text" name="title"/><br/>
        content:<input id="add-blog-content" type="text" name="content"/><br/>
        <input id="addBlogBtn" type="submit" value="submit"/>
    </form>
</div>
<div id="blogsList">
<c:forEach items="${blogs}" var="item">
    <div>
        <a href="${ctp}/blog/${item.id}">${item.title}</a><br/>
        <a href="${ctp}/user/${item.userId}">${item.userName}</a>
        <p>info: ${item.content}</p>
    </div>
</c:forEach>
</div>
<%--page helper--%>
<a href="${ctp}/blog?pn=1">first</a>
<a href="${ctp}/blog?pn=${pageInfo.prePage}">Prev</a>
<c:forEach items="${pageInfo.navigatepageNums}" var="num">
    <c:if test="${num == pageInfo.pageNum}">
        [${num}]
    </c:if>
    <c:if test="${num != pageInfo.pageNum}">
        <a href="${ctp}/blog?pn=${num}">${num}</a>
    </c:if>
</c:forEach>
<a href="${ctp}/blog?pn=${pageInfo.nextPage}">Next</a>
<a href="${ctp}/blog?pn=${pageInfo.pages}">last</a>

<form id="deleteForm" action="${ctp}/menu/${item.id}" method="post">
    <input type="hidden" name="_method" value="delete"/>
</form>
<%--id use $ class use .--%>
<script type="text/javascript">
    function appendBlog(blog) {
        let title = "<a href=\"${ctp}/blog/" + blog.id + "\">" + blog.title +"</a><br/>";
        let username = "<a href=\"${ctp}/user/" + blog.userId + "\">" + blog.userName +"</a>";
        let content = "<p>info:" + blog.content+ "</p>";
        $("#blogsList").prepend("<div>"+ title + username + content +"</div>");
    }

    $("#addBlogBtn").click(function(){
         let blog = {
            title:$("#add-blog-title").val(),
            content:$("#add-blog-content").val()
        }
        let blogStr = JSON.stringify(blog);
        $.ajax({
            url:'${ctp}/addBlog',
            type:'POST',
            data:blogStr,
            dataType:'json',
            contentType:'application/json',
            success:function(data){
                console.log(data);
                $("#add-blog-title").val('');
                $("#add-blog-content").val('');
                appendBlog(data);
            }
        });
        return false;
    });


</script>
</body>
</html>
