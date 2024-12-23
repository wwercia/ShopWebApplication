<%--
  Created by IntelliJ IDEA.
  User: wwerc
  Date: 09.08.2024
  Time: 12:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
    <head>
        <title>Basket</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/basket/basketStyles.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/sidebarStyles.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/basket/basketBookElementStyles.css">
    </head>
    <body>
        <div class="container">

            <%@ include file="../segments/sidebar.jspf" %>

            <main class="content">
                <div class="books-container">

                    <% if (request.getAttribute("areBooksInBasket").equals("true")) { %>
                        <c:forEach var="book" items="${requestScope.resultBooks}">
                            <%@ include file="../segments/basketBookElement.jspf"%>
                        </c:forEach>
                    <% } else { %>
                        <h2 class="emptyBasketText">Your basket is empty!</h2>
                    <% } %>

                </div>

                <div class="subtotalFrame">
                    <div class="subtotal">
                        <h1 class="summaryText">Order summary</h1>
                        <p class="totalText">Total: ${requestScope.orderTotal} zł</p>

                        <% if (request.getAttribute("areBooksInBasket").equals("true")) { %>
                            <a href="addressCheckout" target="_blank" class="checkout-button">
                                Proceed to checkout
                            </a>
                        <% } else { %>
                            <a class="fake-checkout-button">
                                Proceed to checkout
                            </a>
                        <% } %>


                    </div>
                </div>

            </main>
        </div>
        <script src="${pageContext.request.contextPath}/script.js"></script>
    </body>
</html>
