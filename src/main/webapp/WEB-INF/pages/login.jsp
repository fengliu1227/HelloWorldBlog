<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: fengliu
  Date: 5/4/21
  Time: 1:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome to Hello World Blog</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
    <% pageContext.setAttribute("ctp", request.getContextPath()); %>
</head>
<div id="loginDiv">
    <form action="${ctp}/login" method="post">
        Email:<input type="email" name="email"/><br/>
        Password:<input type="password" name="password"/><br/>
        <input id="loginBtn" type="submit" value="Login"/><br/>
        <a href="/login" id="toRegister">Go to Register</a>
    </form>
</div>
<div id="registerDiv" hidden>
    <form:form action="${ctp}/register" modelAttribute="user" method="POST">
        Email:<form:input path="email"/><br/>
        UserName:<form:input path="username"/><br/>
        Password:<form:password path="password"/><br/>
        <input id="registerBtn" type="submit" value="Register"/><br/>
        <a href="/login" id="toLogin">Login</a>
    </form:form>
</div>

<script type="text/javascript">
    $(function(){
        $("#toRegister").click(function(){
            $("#loginDiv").hide();
            $("#registerDiv").show();
            return false;
        })

        $("#toLogin").click(function(){
            $("#registerDiv").hide();
            $("#loginDiv").show();
            return false;
        })
    });
</script>
</body>
</html>
