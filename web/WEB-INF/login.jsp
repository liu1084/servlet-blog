<%--
  Created by IntelliJ IDEA.
  User: jim
  Date: 2017/6/30
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SIGN IN</title>
</head>
<body>
<fieldset>
    <legend>Sign in</legend>
    <form method="post" action="${pageContext.request.contextPath}/login" autocomplete="off">
        <label for="username">username</label>
        <input id="username" value="" type="text"/>
        <label for="password">password</label>
        <input id="password" value="" type="password"/>
        <button>submit</button>
    </form>
</fieldset>
</body>
</html>
