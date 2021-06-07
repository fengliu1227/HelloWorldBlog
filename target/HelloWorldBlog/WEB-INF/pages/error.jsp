<%--
  Created by IntelliJ IDEA.
  User: fengliu
  Date: 5/8/21
  Time: 2:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <% pageContext.setAttribute("ctp", request.getContextPath()); %>
    <link rel="stylesheet" href="${ctp}/static/css/style.css"/>
    <title>Title</title>

    <a href="${ctp}/blog">Home</a>
    <a href="${ctp}/user">Profile</a>
    <a href="${ctp}/logout.do">Logout</a>
</head>
<body class="page-body">
${error}
</body>
</html>
