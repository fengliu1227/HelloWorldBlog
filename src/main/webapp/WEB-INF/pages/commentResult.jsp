<%--
  Created by IntelliJ IDEA.
  User: fengliu
  Date: 6/7/21
  Time: 4:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Result</title>
    <% pageContext.setAttribute("ctp", request.getContextPath()); %>
    <link rel="stylesheet" href="${ctp}/static/css/style.css"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
</head>
<nav>
    <a href="${ctp}/user">Profile</a>
    <a href="${ctp}/logout.do">Logout</a>

</nav>
<body class="page-body">
<div class="page-container" id="blogsList">
    <a class="back-link" href="${ctp}/user">Back</a><br/>
    <c:forEach items="${comments}" var="item">
        <div class="page-div" id="commentResult-comment-${item.id}">
            <a href="${ctp}/blog/${item.blogId}">from this blog(click for more info)</a>
            <p>${item.content}</p>
            <c:if test="${mine}">
                <a href="${ctp}/comment/${item.id}" class="update-comment-commentResult" id="${item.id}">update</a>
                <a href="${ctp}/comment/${item.id}" class="delete-comment-commentResult">delete</a>
            </c:if>
            <c:if test="${userInfo.role.equals(\"ADMIN\")}">
                <a href="${ctp}/comment/${item.id}" class="delete-comment-commentResult">delete</a>
            </c:if>
        </div>
    </c:forEach>
    <%--page helper--%>
    <a href="${ctp}/comment/user/${userId}?pn=1">first</a>
    <a href="${ctp}/comment/user/${userId}?pn=${pageInfo.prePage}">Prev</a>
    <c:forEach items="${pageInfo.navigatepageNums}" var="num">
        <c:if test="${num == pageInfo.pageNum}">
            [${num}]
        </c:if>
        <c:if test="${num != pageInfo.pageNum}">
            <a href="${ctp}/comment/user/${userId}?pn=${num}">${num}</a>
        </c:if>
    </c:forEach>
    <a href="${ctp}/comment/user/${userId}?pn=${pageInfo.nextPage}">Next</a>
    <a href="${ctp}/comment/user/${userId}?pn=${pageInfo.pages}">last</a>
</div>

<form id="deleteForm-commentResult" action="${ctp}/blog" method="post">
    <input type="hidden" name="_method" value="delete"/>
</form>

<script type="text/javascript">
    $(function(){
        $(".delete-comment-commentResult").click(function() {
            $("#deleteForm-commentResult").attr("action",this.href);
            $.ajax({
                url: this.href,
                type: "POST",
                data: $("#deleteForm-commentResult").serialize() +"&_method=Delete",
                success: function (data) {
                    $("#commentResult-comment-"+ data).remove();
                }
            });
            return false;
        });
    });

    $(function(){
        $(".update-comment-commentResult").click(function() {
            let content = $("#commentResult-comment-"+this.id + " p").html();
            let form = "<form action=\"${ctp}/comment/" + this.id +"\" method=\"POST\">\n" +
                "    <input type=\"hidden\" name=\"_method\" value=\"PUT\"/>\n" +
                "    content:<input type=\"text\" name=\"content\" value=\"" + content + "\"/><br/>\n" +
                "    <input class=\"update-comment\" type=\"submit\" value=\"submit\"/>\n" +
                "</form>"
            $("#commentResult-comment-"+this.id).append(form);
            $("#"+this.id).hide();
            return false;
        });
    });

</script>
</body>
</html>
