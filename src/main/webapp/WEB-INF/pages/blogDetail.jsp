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
</head>
<body>
<h1>${blog.title}</h1>
<p>Created by ${blog.userName} on ${createDateStr}</p>
<p>${blog.content}</p>

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
             <dt>${co.content}</dt>
            <dd>by ${co.userName} Post on <fmt:formatDate value="${co.postTime }" pattern="yyyy-MM-dd HH:mm:ss"/></dd><br/>
        </c:forEach>
    </dl>
</div>

<script>
    function appendComment(comment){
        let myDate= new Date(comment.postTime);
        let dateStr = myDate.format('Y-m-d H:i:s');
        let userName = "<dd> by " + comment.userName + " Post on " + dateStr +  "</dd>";
        let content = "<dt>" + comment.content + "</dt>";
        $("#comment-list").prepend(content + userName + "<br/>");
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
</script>
</body>
</html>
