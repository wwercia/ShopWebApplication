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
        <title>registration</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/logAndRegStyles.css">
    </head>
    <body>

        <div class="container">
            <h1>Registration</h1><br>

            <form action="registration" method="post">
                <div>
                    <label for="name">name</label>
                    <input type="text" name="name" id="name">
                </div>
                <div>
                    <label for="surname">surname</label>
                    <input type="text" name="surname" id="surname">
                </div>
                <div>
                    <label for="email">e-mail</label>
                    <input type="email" name="email" id="email">
                </div>
                <div>
                    <label for="password">password</label>
                    <input type="password" name="password" id="password">
                </div>
                <button>Submit</button>
            </form>
        </div>
    </body>
</html>
