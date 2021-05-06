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
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
    <% pageContext.setAttribute("ctp", request.getContextPath()); %>
</head>
<nav>
    <a href="${ctp}/blog">Home</a>
    <a href="${ctp}/logout.do">Logout</a>
    <a href="${ctp}/admin">Admin!</a>
</nav>
<body>


<div id="profile-user-information">
    <ul>
        <li>Username : ${userInfo.username}</li>
        <li>Email: ${userInfo.email}</li>
        <li>Role: ${userInfo.role}</li>
    </ul>
</div>
<div id="profile-Blogs-List">
    <c:forEach items="${blogs}" var="item">
        <div>
            <a href="${ctp}/blog/${item.id}">${item.title}</a><br/>
            <a href="${ctp}/user/${item.userId}">${item.userName}</a>
            <p>info: ${item.content}</p>
        </div>
    </c:forEach>
</div>
<%--page helper--%>
<%--<a href="${ctp}/blog?pn=1">first</a>--%>
<%--<a href="${ctp}/blog?pn=${pageInfo.prePage}">Prev</a>--%>
<%--<c:forEach items="${pageInfo.navigatepageNums}" var="num">--%>
<%--    <c:if test="${num == pageInfo.pageNum}">--%>
<%--        [${num}]--%>
<%--    </c:if>--%>
<%--    <c:if test="${num != pageInfo.pageNum}">--%>
<%--        <a href="${ctp}/blog?pn=${num}">${num}</a>--%>
<%--    </c:if>--%>
<%--</c:forEach>--%>
<%--<a href="${ctp}/blog?pn=${pageInfo.nextPage}">Next</a>--%>
<%--<a href="${ctp}/blog?pn=${pageInfo.pages}">last</a>--%>

<%--<form id="deleteForm" action="${ctp}/menu/${item.id}" method="post">--%>
<%--    <input type="hidden" name="_method" value="delete"/>--%>
<%--</form>--%>
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
