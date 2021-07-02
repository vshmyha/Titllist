<%--
  Created by IntelliJ IDEA.
  User: dkavialkou
  Date: 4/18/2021
  Time: 11:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>Start page</title>
</head>
<body>
<form id="loginForm" class="form" action="controller" method="post">
    ${requestScope.response}
    <h1>Login</h1>
    <input autocomplete='off' id="loginField" type="text" name="username" value="" placeholder="Username"/>
    <input autocomplete='off' id="passwordField" type="password" name="password" value="" placeholder="Password"/>
    <input id="submitLoginBtn" type="submit" value="Log In"/>
    <input type="hidden" name="command" value="login"/>
    <input type="button" onclick="location.href='controller?command=goToRegistrationPage';" value="Sign Up"/>
</form>


</body>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
    <%@include file="../js/start_page.js"%>
</script>
<script>
    <%@include file="../js/common.js"%>
</script>
<style>
    <%@include file="../css/base.css"%>
</style>
</html>