<%--
  Created by IntelliJ IDEA.
  User: jim
  Date: 2017/7/17
  Time: 1:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SIGN UP</title>
</head>
<body>

<fieldset>
    <legend>Sign up</legend>
    <form method="post" action="${pageContext.request.contextPath}/signup" autocomplete="off">
        <ul>
            <li>
                <label for="username">username</label>
                <input id="username" value="" type="text"/>
            </li>
            <li>
                <label for="password">password</label>
                <input id="password" value="" type="password"/>
            </li>
            <li>
                <label for="rePassword">rePassword</label>
                <input id="rePassword" value=""/>
            </li>
            <li>
                <label for="email">email</label>
                <input id="email" value="" type="email"/>
            </li>
            <li>
                <button>submit</button>
            </li>
        </ul>
    </form>
</fieldset>
</body>
</html>
