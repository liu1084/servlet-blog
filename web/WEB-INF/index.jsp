<%@ page import="com.jim.entity.User" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: jim
  Date: 2017/6/30
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<User> users = (List<User>) request.getAttribute("users");
    String appPath = request.getContextPath();
%>
<html>
<head>
    <title>HOME</title>
</head>
<body>
<header>
    <a href="signIn">Sing in</a>
    <a href="signUp">Sing up</a>

    <ul>
        <% for (User user : users) {%>
        <li>

            <label>id</label>
            <span><%=user.getId()%></span>

            <label>username</label>
            <span>
                <a href="<%=request.getContextPath()%>/userUpdate?userId=<%=user.getId()%>">
                    <%=user.getUsername()%>
                </a>
            </span>

            <label>email</label>
            <span><%=user.getEmail()%></span>

            <span>
                <button data-user-id="<%=user.getId()%>" class="delete-user">X</button>
            </span>
        </li>
        <%}%>
    </ul>
</header>

<script>
    var appPath = '<%=appPath%>';
    console.log(appPath);
</script>
<script src="<%=appPath%>/static/node_modules/jquery/dist/jquery.min.js" type="text/javascript"></script>
<script src="<%=appPath%>/static/app/index/index.js" type="text/javascript"></script>

</body>
</html>
