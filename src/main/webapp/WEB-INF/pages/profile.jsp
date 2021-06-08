<%--
  Created by IntelliJ IDEA.
  User: fengliu
  Date: 5/6/21
  Time: 3:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="static/css/style.css"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
    <% pageContext.setAttribute("ctp", request.getContextPath()); %>
</head>
<nav>
    <a href="${ctp}/blog">Home</a>
    <a href="${ctp}/logout.do">Logout</a>
    <c:if test="${userInfo.role.equals(\"ADMIN\")}">
        <a href="${ctp}/admin">Admin!</a>
    </c:if>
</nav>
<body class="page-body">

<div class="functional-container" id="profile-user-information">
    <ul>
        <li>Username : ${userInfo.username}</li>
        <li>Email: ${userInfo.email}</li>
        <li>Role: ${userInfo.role}</li>
    </ul>
</div>

<div class="functional-container">
    <h2 class="page-h2">Search by keyword: </h2>
    <form id="profile-search-form" action="${ctp}/blog/search-this-user" method="GET">
        Title:<input type="text" name="keyword"/><br/>
        <input  type="submit" value="Search Blog"/>
        <input  id="profile-search-comment-btn" type="submit" value="Search Comment"/>
    </form>
</div>

<div class="page-container" id="profile-Blogs-List">
    <c:forEach items="${blogs}" var="item">
        <div class="page-div" id="profile-blog-${item.id}">
            <a class="blog-title" href="${ctp}/blog/${item.id}">${item.title}</a><br/>
            <a class="blog-username" href="${ctp}/user/${item.userId}">${item.userName}</a>
            <p class="blog-info">info: ${item.content}</p>
            <a href="${ctp}/editblog/${item.id}">edit</a>
            <a href="${ctp}/blog/${item.id}" class="delete-in-profile-page">delete</a><br/>
        </div>
    </c:forEach>

    <a href="${ctp}/user?pn=1">first</a>
    <a href="${ctp}/user?pn=${pageInfo.prePage}">Prev</a>
    <c:forEach items="${pageInfo.navigatepageNums}" var="num">
        <c:if test="${num == pageInfo.pageNum}">
            [${num}]
        </c:if>
        <c:if test="${num != pageInfo.pageNum}">
            <a href="${ctp}/user?pn=${num}">${num}</a>
        </c:if>
    </c:forEach>
    <a href="${ctp}/user?pn=${pageInfo.nextPage}">Next</a>
    <a href="${ctp}/user?pn=${pageInfo.pages}">last</a>
</div>

<div class="page-container" id="profile-Comments-List">
    <<c:forEach items="${comments}" var="co">
    <div class="page-div" id="profile-comment-${co.id}">
        <a href="${ctp}/blog/${co.blogId}">from this blog(click for more info)</a>
        <p>${co.content}</p>
        <a href="${ctp}/comment/${co.id}" class="update-comment-in-profile-page" id="${co.id}">update</a>
        <a href="${ctp}/comment/${co.id}" class="delete-comment-in-profile-page">delete</a>
    </div>
</c:forEach>
    <c:if test="${viewAll}">
        <a href="${ctp}/comment/user/${userInfo.id}">View All</a>
    </c:if>
</div>

<form id="deleteForm" action="${ctp}/blog" method="post">
    <input type="hidden" name="_method" value="delete"/>
</form>
<script type="text/javascript">
    $(function(){
        $("#profile-search-comment-btn").click(function(){
            $("#profile-search-form").attr("action","${ctp}/comment/search/${userInfo.id}");
            $("#profile-search-form").submit();
            return false;
        })
    });

    $(function(){
        $(".delete-in-profile-page").click(function() {
            $("#deleteForm").attr("action",this.href);
            $.ajax({
                url: this.href,
                type: "POST",
                data: $("#deleteForm").serialize() +"&_method=Delete",
                success: function (data) {
                    $("#profile-blog-"+ data).remove();
                }
            });
            return false;
        });
    });

    $(function(){
        $(".delete-comment-in-profile-page").click(function() {
            $("#deleteForm").attr("action",this.href);
            $.ajax({
                url: this.href,
                type: "POST",
                data: $("#deleteForm").serialize() +"&_method=Delete",
                success: function (data) {
                    $("#profile-comment-"+ data).remove();
                }
            });
            return false;
        });
    });

    $(function(){
        $(".update-comment-in-profile-page").click(function() {
            let content = $("#profile-comment-"+this.id + " p").html();
            let form = "<form action=\"${ctp}/comment/" + this.id +"\" method=\"POST\">\n" +
                "    <input type=\"hidden\" name=\"_method\" value=\"PUT\"/>\n" +
                "    content:<input type=\"text\" name=\"content\" value=\"" + content + "\"/><br/>\n" +
                "    <input class=\"update-comment\" type=\"submit\" value=\"submit\"/>\n" +
                "</form>"
            $("#profile-comment-"+this.id).append(form);
            $("#"+this.id).hide();
            return false;
        });
    });

</script>
</body>
</html>
