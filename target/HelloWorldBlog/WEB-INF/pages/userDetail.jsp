<%--
  Created by IntelliJ IDEA.
  User: fengliu
  Date: 5/6/21
  Time: 8:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Profile</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
    <% pageContext.setAttribute("ctp", request.getContextPath()); %>
    <link rel="stylesheet" href="${ctp}/static/css/style.css"/>
</head>
<nav>
    <a href="${ctp}/blog">Home</a>
    <a href="${ctp}/user">Profile</a>
    <a href="${ctp}/logout.do">Logout</a>
</nav>
<body class="page-body">


<div class="functional-container" id="userDetail-user-information">
    <ul>
        <li>Username : ${userInfo.username}</li>
        <li>Email: ${userInfo.email}</li>
        <li>Role: ${userInfo.role}</li>
    </ul>
    <c:if test="${curUser.role.equals(\"ADMIN\")}">
        <c:if test="${!userInfo.role.equals(\"ADMIN\")}">
            <input id="update-admin-btn" type="submit" value="update to Admin">
        </c:if>

        <c:if test="${!userInfo.role.equals(\"USER\")}">
            <input id="update-user-btn" type="submit" value="update to User">
        </c:if>

        <c:if test="${!userInfo.role.equals(\"BANNED\")}">
            <input id="update-banned-btn" type="submit" value="Ban this account">
        </c:if>
    </c:if>
</div>

<div class="page-container" id="userDetail-Blogs-List">
    <h2 class="page-h2">Blogs</h2>
    <c:forEach items="${blogs}" var="item">
        <div class="page-div">
            <a class="blog-title" href="${ctp}/blog/${item.id}">${item.title}</a><br/>
            <a class="blog-username" href="${ctp}/user/${item.userId}">${item.userName}</a>
            <p class="blog-info">info: ${item.content}</p>
        </div>
    </c:forEach>

    <a href="${ctp}/user/${userInfo.id}?pn=1">first</a>
    <a href="${ctp}/user/${userInfo.id}?pn=${pageInfo.prePage}">Prev</a>
    <c:forEach items="${pageInfo.navigatepageNums}" var="num">
        <c:if test="${num == pageInfo.pageNum}">
            [${num}]
        </c:if>
        <c:if test="${num != pageInfo.pageNum}">
            <a href="${ctp}/user/${userInfo.id}?pn=${num}">${num}</a>
        </c:if>
    </c:forEach>
    <a href="${ctp}/user/${userInfo.id}?pn=${pageInfo.nextPage}">Next</a>
    <a href="${ctp}/user/${userInfo.id}?pn=${pageInfo.pages}">last</a>
</div>

<div class="page-container" id="userDetail-comment-List">
    <h2 class="page-h2">Comments</h2>
    <c:forEach items="${comments}" var="comment">
        <div class="page-div">
            <a href="${ctp}/comment/${comment.id}">${comment.content}</a><br/>
        </div>
    </c:forEach>
</div>

<form id="update-role-form" action="${ctp}/blog" method="post"></form>

<script type="text/javascript">
    $(function(){
        $("#update-admin-btn").click(function() {
            $("#update-role-form").attr("action","${ctp}/admin/admin/${userInfo.id}");
            $("#update-role-form").submit();
            return false;
        });
    });

    $(function(){
        $("#update-user-btn").click(function() {
            $("#update-role-form").attr("action","${ctp}/admin/user/${userInfo.id}");
            $("#update-role-form").submit();
            return false;
        });
    });

    $(function(){
        $("#update-banned-btn").click(function() {
            $("#update-role-form").attr("action","${ctp}/admin/banned/${userInfo.id}");
            $("#update-role-form").submit();
            return false;
        });
    });

</script>

</body>
</html>
