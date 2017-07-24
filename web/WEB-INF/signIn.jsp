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
    <form method="post" action="${pageContext.request.contextPath}/signIn" autocomplete="off">
        <div>
            <label for="username">username</label>
            <input id="username" name="username" value="" type="text"/>
        </div>
        <div>
            <label for="password">password</label>
            <input id="password" name="password" value="" type="password"/>
        </div>
        <div>
            <button>submit</button>
        </div>
    </form>
</fieldset>
</body>
</html>
