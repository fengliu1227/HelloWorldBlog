<%--
  Created by IntelliJ IDEA.
  User: fengliu
  Date: 6/7/21
  Time: 2:00 PM
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
</head>
<nav>
    <a href="${ctp}/user">Profile</a>
    <a href="${ctp}/logout.do">Logout</a>
    <a href="${ctp}/admin">Admin!</a>
</nav>
<body class="page-body">
<a href="${ctp}/user">Back</a>
<div class="page-container" id="blogsList">
    <c:forEach items="${blogs}" var="item">
        <div class="page-div">
            <a class="blog-title" href="${ctp}/blog/${item.id}">${item.title}</a><br/>
            <a class="blog-username" href="${ctp}/user/${item.userId}">${item.userName}</a>
            <p class="blog-info">info: ${item.content}</p>
            <p class="blog-time"><fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
        </div>
    </c:forEach>
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
</div>

</body>
</html>
