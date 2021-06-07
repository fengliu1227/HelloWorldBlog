<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: fengliu
  Date: 5/4/21
  Time: 7:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Hello Word Blog -${bolg.title}</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/date.format.js"></script>
    <% pageContext.setAttribute("ctp", request.getContextPath()); %>
    <link rel="stylesheet" href="${ctp}/static/css/style.css"/>

    <nav>
        <a href="${ctp}/blog">Home</a>
        <a href="${ctp}/user">Profile</a>
        <a href="${ctp}/logout.do">Logout</a>
    </nav>
</head>
<body>
<h1>${blog.title}</h1>
<p>Created by ${blog.userName} on ${createDateStr}</p>
<c:if test="${viewer.equals(blog.userName)}">
    <a href="${ctp}/editblog/${blog.id}">edit</a>
    <a href="${ctp}/blog/${blog.id}" id="delete-blog-in-detail-page">delete</a>
</c:if>
<p>${blog.content}</p>

<form id="deleteForm" action="${ctp}/blog/${blog.id}" method="post">
    <input type="hidden" name="_method" value="delete"/>
</form>

<div id="add-comment">
    <form action="${ctp}/comment" method="POST">
        <input id="add-comment-blogId" type="hidden" name="blogId" value="${blog.id}"/>
        content:<input id="add-comment-content" type="text" name="content"/><br/>
        <input id="add-comment-btn" type="submit" value="submit"/>
    </form>
</div>

<div>
    <dl id="comment-list">
        <c:forEach items="${RelatedComments}" var="co">
            <div id="comment-${co.id}">
                <dt>${co.content}</dt>
                <dd>by ${co.userName} Post on <fmt:formatDate value="${co.postTime }" pattern="yyyy-MM-dd HH:mm:ss"/></dd><br/>
                <c:if test="${viewer.equals(co.userName)}" >
                    <a href="${ctp}/comment/${co.id}" class="update-comment-in-detail-page" id="${co.id}">update</a>
                    <a href="${ctp}/comment/${co.id}" class="delete-comment-in-detail-page">delete</a>
                </c:if>
            </div>
        </c:forEach>
    </dl>
</div>

<script>
    function appendComment(comment){
        let myDate= new Date(comment.postTime);
        let dateStr = myDate.format('Y-m-d H:i:s');
        let userName = "<dd> by " + comment.userName + " Post on " + dateStr +  "</dd>";
        let content = "<dt>" + comment.content + "</dt>";
        let btns = "<a href=\"${ctp}/comment/"+ comment.id +"\" class=\"update-comment-in-detail-page\" id=\"" + comment.id + "\">update</a>\n" +
            "                    <a href=\"${ctp}/comment/"+ comment.id +"\" class=\"delete-comment-in-detail-page\">delete</a>";
        $("#comment-list").prepend("<div id=\"comment-"+comment.id +"\">"+ content + userName + btns + "<br/>" + "</div>");
    }
    $("#add-comment-btn").click(function(){
        let comment = {
            content:$("#add-comment-content").val(),
            blogId:${blog.id}
        }
        let commentStr = JSON.stringify(comment);
        $.ajax({
            url:'${ctp}/comment',
            type:'POST',
            data:commentStr,
            dataType:'json',
            contentType:'application/json',
            success:function(data){
                $("#add-comment-content").val('');
                appendComment(data);
            }
        });
        return false;
    });

    $(function(){
        $(".delete-comment-in-detail-page").click(function() {
            $("#deleteForm").attr("action",this.href);
            $.ajax({
                url: this.href,
                type: "POST",
                data: $("#deleteForm").serialize() +"&_method=Delete",
                success: function (data) {
                    $("#comment-"+ data).remove();
                }
            });
            return false;
        });
    });

    $(function(){
        $(".update-comment-in-detail-page").click(function() {
            let content = $("#comment-"+this.id +" dt").html();
            let form = "<form action=\"${ctp}/comment/" + this.id +"\" method=\"POST\">\n" +
                "    <input type=\"hidden\" name=\"_method\" value=\"PUT\"/>\n" +
                "    content:<input type=\"text\" name=\"content\" value=\"" + content + "\"/><br/>\n" +
                "    <input class=\"update-comment\" type=\"submit\" value=\"submit\"/>\n" +
                "</form>"
            $("#comment-"+this.id).append(form);
            $("#"+this.id).hide();
            return false;
        });
    });


</script>
</body>
</html>
