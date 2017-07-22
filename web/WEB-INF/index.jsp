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
    <title>HOME</title>
</head>
<body>
<header>
    <%
        StringBuffer query = new StringBuffer();
        int MAX = 7 * 1024 + 487;


        byte[] bytes = "http://localhost:8080/app/login?key=".getBytes();

        for (int i = 0; i < MAX - bytes.length; i++) {
            query.append(1);
        }
        //String link = "http://localhost:8080/app/login?key=" + query;
        String link = "http://localhost:8080/app/login?key1=value1&key2=value2";
    %>
    <a href="<%=link%>">sing in<%=bytes.length%>&<%=link.getBytes().length%>
    </a>
    <a href="signup">sing up</a>
</header>
</body>
</html>
