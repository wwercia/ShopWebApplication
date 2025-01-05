<%--
  Created by IntelliJ IDEA.
  User: wwerc
  Date: 09.08.2024
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
    <head>
        <title>Contact</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/contactStyles.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/sidebarStyles.css">
    </head>
    <body>

        <div class="container">
            <%@ include file="../segments/sidebar.jspf" %>

            <main class="main">
                <div class="content">
                    <div class="content2">
                        <h1>Do you have any questions? Contact us:</h1>
                        <p>Phone Number: +48 111 222 333</p>
                        <p>Monday-Friday: 8:00 - 20:00</p>
                        <p>Weekend: 9:00 - 18:00</p>
                        <p>Email: customer.service@bookshop.com</p>
                    </div>
                </div>
            </main>
        </div>
        <script src="${pageContext.request.contextPath}/script.js"></script>

    </body>
</html>
