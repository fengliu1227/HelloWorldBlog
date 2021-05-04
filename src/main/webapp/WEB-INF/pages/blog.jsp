<%--
  Created by IntelliJ IDEA.
  User: fengliu
  Date: 4/22/21
  Time: 6:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <img src="/images/main.jpg"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
</head>
<body>
<%pageContext.setAttribute("ctp", request.getContextPath());%>
<c:forEach items="${blogs}" var="item">
    <div>
        <a href="">${item.title}</a>
        <p>id: ${item.id}&nbsp;&nbsp;info: ${item.content}&nbsp;&nbsp;price:${item.userId}</p>
        <a href="${ctp}/menu/${item.id}">edit</a>&nbsp;&nbsp;&nbsp;
        <a href="${ctp}/menu/${item.id}" class="deleteBtn">delete</a><br/><br/>
    </div>
</c:forEach>

<form id="deleteForm" action="${ctp}/menu/${item.id}" method="post">
    <input type="hidden" name="_method" value="delete"/>
</form>
<script type="text/javascript">
    $(function(){
       $(".deleteBtn").click(function(){
          $("#deleteForm").attr("action",this.href);
          $("#deleteForm").submit();
          return false;
       })
    });
</script>

<a href="${ctp}/addItem">add new item</a>
</body>
</html>
