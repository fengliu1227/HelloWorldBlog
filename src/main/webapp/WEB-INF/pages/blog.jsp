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
    <a href="${ctp}/addItem">Home</a>
    <a href="${ctp}/addBlog">Write Blog</a>
    <a href="${ctp}/logout.do">Logout</a>
    <a href="${ctp}/admin">Admin!</a>
</nav>
<body>


<div id="addBlog">
    <form action="${ctp}/addBlog" method="POST">
        Title:<input type="text" name="title"/><br/>
        content:<input type="text" name="content"/><br/>
        <input type="submit" value="submit"/>
    </form>
</div>
<div id="blogsList">
<c:forEach items="${blogs}" var="item">
    <div>
        <a href="${ctp}/blog/${item.id}">${item.title}</a>
        <a href="${ctp}/user/${item.userId}">${item.userName}</a>
        <p>info: ${item.content}</p>
    </div>
</c:forEach>
</div>

<form id="deleteForm" action="${ctp}/menu/${item.id}" method="post">
    <input type="hidden" name="_method" value="delete"/>
</form>
<script type="text/javascript">
    $(function(){
       $(".addBlog ").click(function(){
          $("#deleteForm").attr("action",this.href);
          $("#deleteForm").submit();
          return false;
       })
    });
</script>
</body>
</html>
