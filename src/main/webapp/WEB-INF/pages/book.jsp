<%--
  Created by IntelliJ IDEA.
  User: wwerc
  Date: 24.08.2024
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
    <head>
        <title>Book</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/bookStyles.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/sidebarStyles.css">
    </head>

    <body>

        <div class="container">

            <%@ include file="../sidebar.jspf" %>

            <main class="content">
                <h1>Book page!</h1>
            </main>

        </div>

        <script src="${pageContext.request.contextPath}/script.js"></script>

    </body>
</html>
