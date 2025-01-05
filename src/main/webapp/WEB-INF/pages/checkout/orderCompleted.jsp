<%--
  Created by IntelliJ IDEA.
  User: wwerc
  Date: 05.12.2024
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
    <head>
        <title>Checkout</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/checkout/addressCheckoutStyles.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/checkout/checkoutBasicStyles.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/checkout/orderCompletedStyles.css">
    </head>
        <body>
        <div class="container">
            <main class="content1">
                <div class="content2">

                    <h1 class="text">Thanks for ordering!</h1>
                    <div class="back-section">
                        <p>Shop for more:</p>
                        <a href="main" class="logo">BookShop</a>
                    </div>

                </div>
            </main>
        </div>
        <script src="${pageContext.request.contextPath}/script.js"></script>
    </body>
</html>
