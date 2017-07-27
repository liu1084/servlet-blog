<%@ page import="com.jim.entity.User" %>
<%--
  Created by IntelliJ IDEA.
  User: jim
  Date: 2017/7/27
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) request.getAttribute("user");
%>
<html>
<head>
    <title></title>
</head>
<body>
<fieldset>
    <legend>update user</legend>
    <form method="post" action="${pageContext.request.contextPath}/userUpdate" autocomplete="off">
        <ul>
            <li>
                <label for="username">username</label>
                <input id="userId" name="userId" value="<%=user.getId()%>" type="hidden"/>
                <input id="username" name="username" value="<%=user.getUsername()%>" type="text"/>
            </li>
            <li>
                <label for="email">email</label>
                <input id="email" name="email" value="<%=user.getEmail()%>" type="email"/>
            </li>
            <li>
                <button>submit</button>
            </li>
        </ul>
    </form>
</fieldset>
</body>
</html>
