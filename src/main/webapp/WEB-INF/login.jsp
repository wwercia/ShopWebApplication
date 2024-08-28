<%--
  Created by IntelliJ IDEA.
  User: wwerc
  Date: 04.08.2024
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>login</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/logAndRegStyles.css">
    </head>
    <body>

        <div class="container">
            <h1>Log in</h1>
            <form action="log" method="post">
                <div>
                    <label for="email">E-mail</label>
                    <input type="email" name="email" id="email" required>
                </div>
                <div>
                    <label for="password">Password</label>
                    <input type="password" name="password" id="password" required>
                </div>
                <button type="submit">Log in</button>
            </form>
        </div>

    </body>
</html>
