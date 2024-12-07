<%--
  Created by IntelliJ IDEA.
  User: wwerc
  Date: 05.12.2024
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/checkout/deliveryCheckoutStyles.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/checkout/checkoutBasicStyles.css">
    </head>
    <body>
        <div class="container">
            <a href="main" class="logo">BookShop</a>
            <main class="content1">
                <div class="content2">

                    <div class="delivery-options">
                        <h2>Delivery Options</h2>
                        <form method="post" action="processDelivery">
                            <div class="delivery-option">
                                <input type="radio" id="standard" name="delivery" value="standard" checked>
                                <label for="standard">
                                    <div class="delivery-details">
                                        <span class="delivery-type">Standard Delivery</span>
                                        <span class="delivery-price">$5.00</span>
                                        <span class="delivery-time">2-3 business days</span>
                                    </div>
                                </label>
                            </div>
                            <div class="delivery-option">
                                <input type="radio" id="express" name="delivery" value="express">
                                <label for="express">
                                    <div class="delivery-details">
                                        <span class="delivery-type">Express Delivery</span>
                                        <span class="delivery-price">$10.00</span>
                                        <span class="delivery-time">Next day</span>
                                    </div>
                                </label>
                            </div>
                            <div class="delivery-option">
                                <input type="radio" id="pickup" name="delivery" value="pickup">
                                <label for="pickup">
                                    <div class="delivery-details">
                                        <span class="delivery-type">Pick-up at store</span>
                                        <span class="delivery-price">Free</span>
                                        <span class="delivery-time">Ready in 2 hours</span>
                                    </div>
                                </label>
                            </div>
                            <button type="submit" class="confirm-delivery">Confirm Delivery Method</button>
                        </form>
                    </div>

                </div>
            </main>
        </div>
        <script src="${pageContext.request.contextPath}/script.js"></script>
    </body>
</html>
