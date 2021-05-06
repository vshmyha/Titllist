<%--
  Created by IntelliJ IDEA.
  User: LAS
  Date: 4/19/2021
  Time: 6:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>registration</title>
</head>
<body>
<form class="form" id="registrationForm" action="controller" method="post">
    <h1>SignUp</h1>
    <input type="hidden" name="command" value="registration">
    <input id="registrationField" type="text" name="username" value="" placeholder="Username"/>
    <input id="passwordField" type="password" name="password" value="" placeholder="Password"/>
    <input id="repeatedPasswordField" type="password" name="password" value="" placeholder="Repeat password"/>
    <input id="submitRegistrationBtn" type="submit" value="Sign up"/>
</form>
<p id="errorMessage">
</p>
</body>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
    <%@include file="../js/registration.js"%>
</script>
<script>
    <%@include file="../js/common.js"%>
</script>
<style>
    <%@include file="../css/base.css"%>
</style>
</html>
