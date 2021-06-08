<%--
  Created by IntelliJ IDEA.
  User: fengliu
  Date: 6/7/21
  Time: 4:06 PM
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
    <a href="${ctp}/blog">Home</a>
    <a href="${ctp}/user">Profile</a>
    <a href="${ctp}/logout.do">Logout</a>
    <a href="${ctp}/admin">Admin!</a>
</nav>
<body class="page-body">
<div class="page-container" id="user-search-List">
    <a class="back-link" href="${ctp}/blog">Back to Main page</a><br/>
    <c:forEach items="${users}" var="item">
        <div class="page-div">
            <a class="user-username" href="${ctp}/user/${item.id}">${item.username}</a><br/>
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
