<%--
  Created by IntelliJ IDEA.
  User: dkavialkou
  Date: 4/18/2021
  Time: 11:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Start page</title>
</head>
<body>
<form action="controller" method="post">
    <label>
        Username:
        <input type="text" name="username" value=""/>
    </label>
    <label>
        Password:
        <input type="password" name="password" value=""/>
    </label>
    <input type="submit" value="LogIn"/>
    <input type="hidden" name="command" value="login"/>
</form>
${requestScope.response}
</body>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</html>
