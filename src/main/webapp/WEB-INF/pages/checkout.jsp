<%--
  Created by IntelliJ IDEA.
  User: wwerc
  Date: 28.11.2024
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
    <head>
        <title>Checkout</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/checkoutStyles.css">
    </head>
    <body>
        <div class="container">
            <a href="main" class="logo">BookShop</a>
            <main class="content1">
                <div class="content2">

                    <div class="address">
                        <p>Add delivery address</p>
                        <form>
                            <label for="firstName">First name</label>
                            <input type="text" id="firstName">
                            <label for="lastName">Last name</label>
                            <input type="text" id="lastName">
                            <label for="phoneNumber">Phone number (no spaces)</label>
                            <input type="text" id="phoneNumber">
                            <label for="address">Address</label>
                            <input type="text" id="address">
                            <label for="city">Town/City</label>
                            <input type="text" id="city">


                        </form>
                    </div>

                </div>
            </main>
        </div>
        <script src="${pageContext.request.contextPath}/script.js"></script>
    </body>
</html>
