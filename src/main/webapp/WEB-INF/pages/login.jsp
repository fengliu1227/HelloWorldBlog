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
    <link rel="stylesheet" href="${ctp}/static/css/style.css"/>
</head>
<body class="login-body">
<div id="loginDiv">
    <h1>Login</h1>
    <form action="login.do" method="post">
        <input class="input-text" type="text" name="username" placeholder="Username"/><br/>
        <input class="input-text" type="password"  name="password" placeholder="Password"/><br/>
        <input id="loginBtn" class="input-btn" type="submit" value="Login"/><br/>
        <a href="/login" class="loginPage-switch" id="toRegister">Go to Register</a>
    </form>
</div>
<div id="registerDiv" hidden>
    <h1>Register</h1>
    <form:form action="${ctp}/register" modelAttribute="userInfo" method="POST">
        <form:input class="input-text" path="username" placeholder="Username"/><br/>
        <form:input class="input-text" path="email" placeholder="Email"/><br/>
        <form:password class="input-text" path="password" placeholder="Password"/><br/>
        <input id="registerBtn" class="input-btn" type="submit" value="Register"/><br/>
        <a href="/login" class="loginPage-switch" id="toLogin">Login</a>
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
