<%--
  Created by IntelliJ IDEA.
  User: fengliu
  Date: 5/5/21
  Time: 8:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% pageContext.setAttribute("ctp", request.getContextPath()); %>
<link rel="stylesheet" href="${ctp}/static/css/style.css"/>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<html>
<head>
    <title>Admin</title>
</head>
<body class="page-body">
This is the page for admin!!!

<div class="functional-container">
    <h2 class="page-h2">Search Blog by keyword</h2>
    <form id="admin-search-form" action="${ctp}/blog/search/admin" method="GET">
        Title:<input type="text" name="keyword"/><br/>
        <input  type="submit" value="Search Blog"/>
        <input  id="admin-user-btn" type="submit" value="Search User"/>
        <input  id="admin-comment-btn" type="submit" value="Search Comment"/>
    </form>
</div>
<script type="text/javascript">
    $(function(){
        $("#admin-user-btn").click(function(){
            $("#admin-search-form").attr("action","${ctp}/user/search");
            $("#admin-search-form").submit();
            return false;
        })
    });

    $(function(){
        $("#admin-comment-btn").click(function(){
            $("#admin-search-form").attr("action","${ctp}/comment/search/admin");
            $("#admin-search-form").submit();
            return false;
        })
    });

</script>
</body>
</html>
