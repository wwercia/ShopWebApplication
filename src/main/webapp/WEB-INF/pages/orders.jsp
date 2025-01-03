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
        <title>Orders</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/ordersStyles.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/sidebarStyles.css">
    </head>
    <body>

        <div class="container">
            <%@ include file="../segments/sidebar.jspf" %>

            <main class="content">
                <h1 class="orders-text">Your orders:</h1>
                <% if(request.getAttribute("isResult").equals("true")){ %>
                    <div class="orders">
                        <c:forEach var="singleOrderElement" items="${requestScope.result}">
                            <div class="single-order">
                                <h2>Numer zamówienia: ${singleOrderElement.orderId}</h2>
                                <c:forEach var="book" items="${singleOrderElement.orderedBooks}">
                                    <%@ include file="../segments/orderBookElement.jspf"%>
                                </c:forEach>
                                <p class="total-text">Total: ${singleOrderElement.total}</p>
                                <p class="delivery-cost">+ Delivery cost: ${singleOrderElement.deliveryCost} zł</p>
                            </div>
                        </c:forEach>
                    </div>
                <% } else { %>
                    <h2 class="no-orders-text">You don't have any orders right now. Time to go shopping...</h2>
                <% } %>

            </main>
        </div>
        <script src="${pageContext.request.contextPath}/script.js"></script>

    </body>
</html>
