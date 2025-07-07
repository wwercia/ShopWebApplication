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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/products/bookStyles.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/sidebarStyles.css">
    </head>

    <body>

        <div class="container">

            <%@ include file="../segments/sidebar.jspf" %>

            <main class="content">
                <div class="top-box">
                    <a href="main" class="logo">BookShop</a>
                    <form action="search" method="get" class="search-form">
                        <input class="searchedTextField" type="text" id="searchedText" name="searchedText" placeholder="What are you looking for?" autocomplete="off">
                        <button type="submit" hidden="hidden"></button>
                    </form>
                </div>

                <hr class="custom-hr">

                <div class="content2">
                    <img class="image" src="${pageContext.request.contextPath}/images/${requestScope.bookInfo.title}.png" alt="image of the book">
                    <div class="info">
                        <p class="book-title" title="${requestScope.bookInfo.title}"><c:out value="${requestScope.bookInfo.title}"/></p>
                        <p class="book-author" title="${requestScope.bookInfo.author}"><c:out value="${requestScope.bookInfo.author}"/></p>
                        <p class="book-rating"><c:out value="${requestScope.bookInfo.rating}"/> / 5</p>
                        <p class="book-price">Price: <c:out value="${requestScope.bookInfo.price}"/>zł</p>
                        <p class="book-publisher" title="${requestScope.bookInfo.publisher}">Publisher: <c:out value="${requestScope.bookInfo.publisher}"/></p>
                        <p class="book-series">Series: <c:out value="${requestScope.bookInfo.series}"/></p>
                        <p class="book-pages">Pages: <c:out value="${requestScope.bookInfo.pages}"/></p>
                        <p class="book-cover">Format: <c:out value="${requestScope.bookInfo.cover}"/></p>
                        <p class="book-publicationDate">Publication date: <c:out value="${requestScope.bookInfo.publicationDate}"/></p>
                        <p class="book-description">Description: <c:out value="${requestScope.bookInfo.description}"/></p>
                    </div>
                    <% if (request.getAttribute("areBooksInSeries").equals("true")) { %>
                    <div class="books-in-series-container">
                        <h2 class="other-books-text">Other books in series:</h2>
                        <div class="books-container">
                            <c:forEach var="book" items="${requestScope.booksInSeries}">
                                <div class="single-element-box">
                                    <div class="book">
                                        <img src="${pageContext.request.contextPath}/images/${book.title}.png" alt="image of the book" class="book-imagee">
                                        <div class="book-info">
                                            <a href="book?bookTitle=${book.title}" class="book-titlee" title="${book.title}">
                                                <c:out value="${book.title}"/>
                                            </a>
                                            <p class="book-pricee"><c:out value="${book.price}"/>zł</p>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <% } else { %>
                        <div class="background"></div>
                    <% } %>
                </div>

                <form action="basket" method="post">
                    <input type="hidden" name="bookTitle" value="${requestScope.bookInfo.title}">
                    <button type="submit" class="add-to-basket">Add to Basket</button>
                </form>

            </main>
        </div>
        <script src="${pageContext.request.contextPath}/script.js"></script>
    </body>
</html>
