<%--
  Created by IntelliJ IDEA.
  User: wwerc
  Date: 04.08.2024
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Welcome</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/logAndRegStyles.css">
    </head>
    <body>

        <div class="container">
            <h1>Welcome to BookShop!</h1>
            <form action="log" method="get">
                <button>Log in</button>
            </form>
            <br>
            <form action="registration" method="get">
                <button>Register</button>
            </form>
        </div>

    </body>
</html>
