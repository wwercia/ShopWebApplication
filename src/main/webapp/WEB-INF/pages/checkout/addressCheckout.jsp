<%--
  Created by IntelliJ IDEA.
  User: wwerc
  Date: 05.12.2024
  Time: 14:36
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
    </head>
    <body>
        <div class="container">
            <a href="main" class="logo">BookShop</a>
            <main class="content1">
                <div class="content2">
                    <div class="address">

                        <p class="address-title">Add delivery address</p>

                        <form action="addressCheckout" method="post" class="checkout-form">
                            <label for="phoneNumber" class="form-label">Phone number (no spaces)</label>
                            <input type="text" id="phoneNumber" name="phoneNumber" class="form-input">

                            <label for="address" class="form-label">Address</label>
                            <input type="text" id="address" name="address" class="form-input">

                            <label for="town" class="form-label">Town/City</label>
                            <input type="text" id="town" name="town" class="form-input">

                            <label for="postcode" class="form-label">Postcode</label>
                            <input type="text" id="postcode" name="postcode" class="form-input">

                            <% if(request.getAttribute("incorrectData") != null) { %>
                                <p class="incorrect-data">Incorrect data!</p>
                            <% } %>

                            <button class="submit-button">Use this address</button>
                        </form>
                    </div>
                </div>
            </main>
        </div>
        <script src="${pageContext.request.contextPath}/script.js"></script>
    </body>
</html>
