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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/checkout/checkoutStyles.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/checkout/checkoutBasicStyles.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/checkout/checkoutBookElementStyles.css">
    </head>
    <body>
        <div class="container">
            <a href="main" class="logo">BookShop</a>
            <main class="content1">
                <div class="content2">

                    <div class="order-information">
                        <div class="user-address">
                            <h2 class="address-title">Your address:</h2>
                            <p class="address-detail">Phone number: ${requestScope.addressData.phoneNumber}</p>
                            <p class="address-detail">Address: ${requestScope.addressData.address}</p>
                            <p class="address-detail">Town/City: ${requestScope.addressData.town}</p>
                            <p class="address-detail">Postcode: ${requestScope.addressData.postcode}</p>
                        </div>

                        <div class="products">
                            <c:forEach var="book" items="${requestScope.books}">
                                <%@ include file="../../segments/checkoutBookElement.jspf"%>
                            </c:forEach>
                        </div>

                        <div class="delivery">
                            <h3>Delivery method: </h3>
                            <p class="delivery-method-text">${requestScope.deliveryMethod}</p>
                            <form action="addressCheckout" method="get" class="delivery-button-form">
                                <button class="delivery-change-button">Change</button>
                            </form>
                        </div>
                    </div>


                    <div class="final-information">

                        <h3 class="text">Basket value: ${requestScope.orderTotal} zł</h3>
                        <h3 class="text">Delivery cost: ${requestScope.deliveryCost} zł</h3>

                        <form action="checkout" method="post" class="checkout-form">
                            <button class="pay-button">Order and pay</button>
                        </form>

                    </div>

                </div>
            </main>
        </div>
        <script src="${pageContext.request.contextPath}/script.js"></script>
    </body>
</html>
