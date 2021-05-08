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
            <a href="${ctp}/editblog/${item.id}">edit</a>
            <a href="${ctp}/blog/${item.id}" class="delete-in-profile-page">delete</a><br/>
        </div>
    </c:forEach>
</div>
========================================================
<div id="profile-Comments-List">
    <c:forEach items="${comments}" var="co">
        <div id="profile-comment-${co.id}">
            <a href="${ctp}/blog/${co.blogId}">from this blog(click for more info)</a>
            <p>${co.content}</p>
            <a href="${ctp}/comment/${co.id}" class="update-comment-in-profile-page" id="${co.id}">update</a>
            <a href="${ctp}/comment/${co.id}" class="delete-comment-in-profile-page">delete</a>
        </div>
    </c:forEach>
</div>
<form id="deleteForm" action="${ctp}/blog" method="post">
    <input type="hidden" name="_method" value="delete"/>
</form>
<script type="text/javascript">
    $(function(){
        $(".delete-in-profile-page").click(function(){
            $("#deleteForm").attr("action",this.href);
            $("#deleteForm").submit();
            return false;
        })
    });
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
