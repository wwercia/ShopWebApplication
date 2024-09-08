<%--
  Created by IntelliJ IDEA.
  User: wwerc
  Date: 04.08.2024
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Book Shop</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/mainPageStyles.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/sidebarStyles.css">
    </head>
    <body>

        <div class="container">
            <!-- Pasek boczny -->
            <%@ include file="segments/sidebar.jspf" %>

            <div class="box">
                <!-- Główna część strony -->
                <main class="content">

                    <h1>Welcome to BookShop!</h1>
                    <div class="books-section">

                        <div class="books-box">
                            <h2>Recommended books</h2>
                            <div class="books-container">
                                <c:forEach var="book" items="${requestScope.recommendedBooks}">
                                    <%@ include file="segments/bookElement.jspf" %>
                                </c:forEach>
                            </div>
                        </div>

                        <div class="books-box">
                            <h2>Best rated books</h2>
                            <div class="books-container">
                                <c:forEach var="book" items="${requestScope.bestRatedBooks}">

                                    <%@ include file="segments/bookElement.jspf" %>

                                </c:forEach>
                            </div>

                        </div>
                    </div>

                </main>
            </div>
        </div>

    </body>
</html>
